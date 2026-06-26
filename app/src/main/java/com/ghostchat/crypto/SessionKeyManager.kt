package com.ghostchat.crypto

import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@Singleton
class SessionKeyManager @Inject constructor(
    private val secureMemoryCleaner: SecureMemoryCleaner,
) {
    private val mutex = Mutex()
    private var sessionKeyBytes: ByteArray? = null
    private val _hasSessionKey = MutableStateFlow(false)
    val hasSessionKey: StateFlow<Boolean> = _hasSessionKey

    suspend fun replaceSessionKey(newKeyBytes: ByteArray) {
        mutex.withLock {
            destroySessionKeyLocked()
            sessionKeyBytes = newKeyBytes.copyOf()
            secureMemoryCleaner.clean(newKeyBytes)
            _hasSessionKey.value = true
        }
    }

    suspend fun <T> useSessionKey(block: (SecretKey) -> T): T? {
        val keyCopy = mutex.withLock { sessionKeyBytes?.copyOf() } ?: return null
        return try {
            block(SecretKeySpec(keyCopy, AES_ALGORITHM))
        } finally {
            secureMemoryCleaner.clean(keyCopy)
        }
    }

    suspend fun destroySessionKey() {
        mutex.withLock {
            destroySessionKeyLocked()
        }
    }

    private fun destroySessionKeyLocked() {
        secureMemoryCleaner.clean(sessionKeyBytes)
        sessionKeyBytes = null
        _hasSessionKey.value = false
    }

    companion object {
        private const val AES_ALGORITHM = "AES"
    }
}
