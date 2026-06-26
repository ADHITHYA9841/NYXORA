package com.ghostchat.crypto

import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AESGCMEncryptionManager @Inject constructor() {
    private val secureRandom = SecureRandom()

    fun encrypt(
        plaintext: ByteArray,
        key: SecretKey,
        associatedData: ByteArray = DEFAULT_ASSOCIATED_DATA,
    ): EncryptedPayload {
        val nonce = ByteArray(NONCE_BYTES)
        secureRandom.nextBytes(nonce)

        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, key, GCMParameterSpec(TAG_BITS, nonce))
        cipher.updateAAD(associatedData)

        return EncryptedPayload(
            nonce = nonce,
            ciphertextAndTag = cipher.doFinal(plaintext),
        )
    }

    fun decrypt(
        payload: EncryptedPayload,
        key: SecretKey,
        associatedData: ByteArray = DEFAULT_ASSOCIATED_DATA,
    ): ByteArray {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(TAG_BITS, payload.nonce))
        cipher.updateAAD(associatedData)
        return cipher.doFinal(payload.ciphertextAndTag)
    }

    companion object {
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val NONCE_BYTES = 12
        private const val TAG_BITS = 128
        private val DEFAULT_ASSOCIATED_DATA = "GhostChat-v1-message".toByteArray(Charsets.UTF_8)
    }
}
