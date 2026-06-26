package com.ghostchat.crypto

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class ECDHKeyExchangeManagerTest {
    private val cleaner = SecureMemoryCleaner()
    private val manager = ECDHKeyExchangeManager(cleaner)

    @Test
    fun deriveAes256Key_returnsSameKeyForBothPeers() {
        val alice = manager.generateKeyExchangeMaterial()
        val bob = manager.generateKeyExchangeMaterial()

        val aliceKey = manager.deriveAes256Key(alice.keyPair.private, bob.encodedPublicKey)
        val bobKey = manager.deriveAes256Key(bob.keyPair.private, alice.encodedPublicKey)

        assertEquals(32, aliceKey.size)
        assertArrayEquals(aliceKey, bobKey)

        cleaner.clean(aliceKey)
        cleaner.clean(bobKey)
        cleaner.clean(alice.encodedPublicKey)
        cleaner.clean(bob.encodedPublicKey)
    }
}
