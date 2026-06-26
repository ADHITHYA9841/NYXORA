package com.ghostchat.crypto;

import javax.crypto.AEADBadTagException;
import javax.crypto.spec.SecretKeySpec;
import org.junit.Test;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\bH\u0007J\b\u0010\n\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/ghostchat/crypto/AESGCMEncryptionManagerTest;", "", "()V", "key", "Ljavax/crypto/spec/SecretKeySpec;", "manager", "Lcom/ghostchat/crypto/AESGCMEncryptionManager;", "decrypt_rejectsTamperedCiphertext", "", "encryptThenDecrypt_returnsOriginalPlaintext", "encrypt_doesNotEmbedPlaintextInWirePayload", "app_debugUnitTest"})
public final class AESGCMEncryptionManagerTest {
    @org.jetbrains.annotations.NotNull()
    private final com.ghostchat.crypto.AESGCMEncryptionManager manager = null;
    @org.jetbrains.annotations.NotNull()
    private final javax.crypto.spec.SecretKeySpec key = null;
    
    public AESGCMEncryptionManagerTest() {
        super();
    }
    
    @org.junit.Test()
    public final void encryptThenDecrypt_returnsOriginalPlaintext() {
    }
    
    @org.junit.Test()
    public final void encrypt_doesNotEmbedPlaintextInWirePayload() {
    }
    
    @org.junit.Test()
    public final void decrypt_rejectsTamperedCiphertext() {
    }
}