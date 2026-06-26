package com.ghostchat.crypto

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SessionKeyManagerTest {
    private val cleaner = SecureMemoryCleaner()
    private val manager = SessionKeyManager(cleaner)

    @Test
    fun replaceSessionKey_wipesInputAndAllowsKeyUse() = runTest {
        val keyBytes = ByteArray(32) { 7 }

        manager.replaceSessionKey(keyBytes)

        assertTrue(keyBytes.all { it.toInt() == 0 })
        assertTrue(manager.hasSessionKey.value)
        assertEquals("AES", manager.useSessionKey { it.algorithm })
    }

    @Test
    fun destroySessionKey_removesKeyMaterial() = runTest {
        manager.replaceSessionKey(ByteArray(32) { 9 })

        manager.destroySessionKey()

        assertFalse(manager.hasSessionKey.value)
        assertNull(manager.useSessionKey { it.algorithm })
    }
}
