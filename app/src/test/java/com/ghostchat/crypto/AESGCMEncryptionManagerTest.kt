package com.ghostchat.crypto

import javax.crypto.AEADBadTagException
import javax.crypto.spec.SecretKeySpec
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Test

class AESGCMEncryptionManagerTest {
    private val manager = AESGCMEncryptionManager()
    private val key = SecretKeySpec(ByteArray(32) { it.toByte() }, "AES")

    @Test
    fun encryptThenDecrypt_returnsOriginalPlaintext() {
        val plaintext = "message that must never cross Bluetooth as plaintext".toByteArray()

        val payload = manager.encrypt(plaintext, key)
        val decrypted = manager.decrypt(payload, key)

        assertArrayEquals(plaintext, decrypted)
    }

    @Test
    fun encrypt_doesNotEmbedPlaintextInWirePayload() {
        val plaintext = "ghostchat-secret".toByteArray()

        val payload = manager.encrypt(plaintext, key).toWireBytes()

        assertFalse(payload.toString(Charsets.ISO_8859_1).contains("ghostchat-secret"))
    }

    @Test
    fun decrypt_rejectsTamperedCiphertext() {
        val payload = manager.encrypt("trusted".toByteArray(), key)
        val tampered = payload.ciphertextAndTag.copyOf()
        tampered[tampered.lastIndex] = (tampered.last().toInt() xor 0x01).toByte()

        assertThrows(AEADBadTagException::class.java) {
            manager.decrypt(payload.copy(ciphertextAndTag = tampered), key)
        }
    }
}
