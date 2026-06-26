package com.ghostchat.crypto;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0006J \u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/ghostchat/crypto/AESGCMEncryptionManager;", "", "()V", "secureRandom", "Ljava/security/SecureRandom;", "decrypt", "", "payload", "Lcom/ghostchat/crypto/EncryptedPayload;", "key", "Ljavax/crypto/SecretKey;", "associatedData", "encrypt", "plaintext", "Companion", "app_debug"})
public final class AESGCMEncryptionManager {
    @org.jetbrains.annotations.NotNull()
    private final java.security.SecureRandom secureRandom = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int NONCE_BYTES = 12;
    private static final int TAG_BITS = 128;
    @org.jetbrains.annotations.NotNull()
    private static final byte[] DEFAULT_ASSOCIATED_DATA = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.ghostchat.crypto.AESGCMEncryptionManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public AESGCMEncryptionManager() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ghostchat.crypto.EncryptedPayload encrypt(@org.jetbrains.annotations.NotNull()
    byte[] plaintext, @org.jetbrains.annotations.NotNull()
    javax.crypto.SecretKey key, @org.jetbrains.annotations.NotNull()
    byte[] associatedData) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] decrypt(@org.jetbrains.annotations.NotNull()
    com.ghostchat.crypto.EncryptedPayload payload, @org.jetbrains.annotations.NotNull()
    javax.crypto.SecretKey key, @org.jetbrains.annotations.NotNull()
    byte[] associatedData) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/ghostchat/crypto/AESGCMEncryptionManager$Companion;", "", "()V", "DEFAULT_ASSOCIATED_DATA", "", "NONCE_BYTES", "", "TAG_BITS", "TRANSFORMATION", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}