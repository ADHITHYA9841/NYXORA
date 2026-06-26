package com.ghostchat.crypto;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u000bJ(\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/ghostchat/crypto/ECDHKeyExchangeManager;", "", "secureMemoryCleaner", "Lcom/ghostchat/crypto/SecureMemoryCleaner;", "(Lcom/ghostchat/crypto/SecureMemoryCleaner;)V", "deriveAes256Key", "", "privateKey", "Ljava/security/PrivateKey;", "peerEncodedPublicKey", "generateKeyExchangeMaterial", "Lcom/ghostchat/crypto/KeyExchangeMaterial;", "hkdfSha256", "inputKeyMaterial", "salt", "info", "outputLength", "", "Companion", "app_debug"})
public final class ECDHKeyExchangeManager {
    @org.jetbrains.annotations.NotNull()
    private final com.ghostchat.crypto.SecureMemoryCleaner secureMemoryCleaner = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CURVE_NAME = "secp256r1";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String HMAC_SHA256 = "HmacSHA256";
    private static final int AES_256_BYTES = 32;
    @org.jetbrains.annotations.NotNull()
    private static final byte[] HKDF_SALT = null;
    @org.jetbrains.annotations.NotNull()
    private static final byte[] HKDF_INFO = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.ghostchat.crypto.ECDHKeyExchangeManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public ECDHKeyExchangeManager(@org.jetbrains.annotations.NotNull()
    com.ghostchat.crypto.SecureMemoryCleaner secureMemoryCleaner) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ghostchat.crypto.KeyExchangeMaterial generateKeyExchangeMaterial() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] deriveAes256Key(@org.jetbrains.annotations.NotNull()
    java.security.PrivateKey privateKey, @org.jetbrains.annotations.NotNull()
    byte[] peerEncodedPublicKey) {
        return null;
    }
    
    private final byte[] hkdfSha256(byte[] inputKeyMaterial, byte[] salt, byte[] info, int outputLength) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/ghostchat/crypto/ECDHKeyExchangeManager$Companion;", "", "()V", "AES_256_BYTES", "", "CURVE_NAME", "", "HKDF_INFO", "", "HKDF_SALT", "HMAC_SHA256", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}