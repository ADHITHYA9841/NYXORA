package com.ghostchat.crypto

import java.nio.ByteBuffer

data class EncryptedPayload(
    val nonce: ByteArray,
    val ciphertextAndTag: ByteArray,
) {
    fun toWireBytes(): ByteArray {
        val buffer = ByteBuffer.allocate(Int.SIZE_BYTES + nonce.size + Int.SIZE_BYTES + ciphertextAndTag.size)
        buffer.putInt(nonce.size)
        buffer.put(nonce)
        buffer.putInt(ciphertextAndTag.size)
        buffer.put(ciphertextAndTag)
        return buffer.array()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EncryptedPayload) return false
        return nonce.contentEquals(other.nonce) &&
            ciphertextAndTag.contentEquals(other.ciphertextAndTag)
    }

    override fun hashCode(): Int {
        var result = nonce.contentHashCode()
        result = 31 * result + ciphertextAndTag.contentHashCode()
        return result
    }

    companion object {
        private const val MAX_NONCE_BYTES = 32
        private const val MAX_CIPHERTEXT_BYTES = 64 * 1024

        fun fromWireBytes(bytes: ByteArray): EncryptedPayload {
            val buffer = ByteBuffer.wrap(bytes)
            val nonceSize = buffer.int
            require(nonceSize in 1..MAX_NONCE_BYTES) { "Invalid nonce size" }
            require(buffer.remaining() >= nonceSize + Int.SIZE_BYTES) { "Malformed payload" }

            val nonce = ByteArray(nonceSize)
            buffer.get(nonce)

            val ciphertextSize = buffer.int
            require(ciphertextSize in 1..MAX_CIPHERTEXT_BYTES) { "Invalid ciphertext size" }
            require(buffer.remaining() == ciphertextSize) { "Malformed payload" }

            val ciphertextAndTag = ByteArray(ciphertextSize)
            buffer.get(ciphertextAndTag)
            return EncryptedPayload(nonce, ciphertextAndTag)
        }
    }
}
