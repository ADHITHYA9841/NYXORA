package com.ghostchat.crypto

import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.SecureRandom
import java.security.spec.ECGenParameterSpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.KeyAgreement
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.ceil

data class KeyExchangeMaterial(
    val keyPair: KeyPair,
    val encodedPublicKey: ByteArray,
)

@Singleton
class ECDHKeyExchangeManager @Inject constructor(
    private val secureMemoryCleaner: SecureMemoryCleaner,
) {
    fun generateKeyExchangeMaterial(): KeyExchangeMaterial {
        val keyPairGenerator = KeyPairGenerator.getInstance("EC")
        keyPairGenerator.initialize(ECGenParameterSpec(CURVE_NAME), SecureRandom())
        val keyPair = keyPairGenerator.generateKeyPair()
        return KeyExchangeMaterial(
            keyPair = keyPair,
            encodedPublicKey = keyPair.public.encoded,
        )
    }

    fun deriveAes256Key(
        privateKey: PrivateKey,
        peerEncodedPublicKey: ByteArray,
    ): ByteArray {
        val keyFactory = KeyFactory.getInstance("EC")
        val peerPublicKey = keyFactory.generatePublic(X509EncodedKeySpec(peerEncodedPublicKey))
        val agreement = KeyAgreement.getInstance("ECDH")
        agreement.init(privateKey)
        agreement.doPhase(peerPublicKey, true)

        val sharedSecret = agreement.generateSecret()
        return try {
            hkdfSha256(
                inputKeyMaterial = sharedSecret,
                salt = HKDF_SALT,
                info = HKDF_INFO,
                outputLength = AES_256_BYTES,
            )
        } finally {
            secureMemoryCleaner.clean(sharedSecret)
        }
    }

    private fun hkdfSha256(
        inputKeyMaterial: ByteArray,
        salt: ByteArray,
        info: ByteArray,
        outputLength: Int,
    ): ByteArray {
        val extractMac = Mac.getInstance(HMAC_SHA256)
        extractMac.init(SecretKeySpec(salt, HMAC_SHA256))
        val pseudorandomKey = extractMac.doFinal(inputKeyMaterial)

        return try {
            val expandMac = Mac.getInstance(HMAC_SHA256)
            expandMac.init(SecretKeySpec(pseudorandomKey, HMAC_SHA256))
            val hashLength = expandMac.macLength
            val iterations = ceil(outputLength.toDouble() / hashLength.toDouble()).toInt()
            require(iterations <= 255) { "HKDF output length is too large" }

            val output = ByteArray(outputLength)
            var previous = ByteArray(0)
            var generated = 0

            for (counter in 1..iterations) {
                expandMac.reset()
                expandMac.update(previous)
                expandMac.update(info)
                expandMac.update(counter.toByte())
                previous = expandMac.doFinal()

                val bytesToCopy = minOf(previous.size, outputLength - generated)
                previous.copyInto(output, generated, 0, bytesToCopy)
                generated += bytesToCopy
            }

            secureMemoryCleaner.clean(previous)
            output
        } finally {
            secureMemoryCleaner.clean(pseudorandomKey)
        }
    }

    companion object {
        private const val CURVE_NAME = "secp256r1"
        private const val HMAC_SHA256 = "HmacSHA256"
        private const val AES_256_BYTES = 32
        private val HKDF_SALT = "GhostChat-ECDH-v1".toByteArray(Charsets.UTF_8)
        private val HKDF_INFO = "GhostChat Bluetooth AES-256-GCM session".toByteArray(Charsets.UTF_8)
    }
}
