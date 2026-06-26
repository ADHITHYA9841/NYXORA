package com.ghostchat.crypto;

import java.nio.ByteBuffer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\u0006\u0010\u0013\u001a\u00020\u0003R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0015"}, d2 = {"Lcom/ghostchat/crypto/EncryptedPayload;", "", "nonce", "", "ciphertextAndTag", "([B[B)V", "getCiphertextAndTag", "()[B", "getNonce", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "toWireBytes", "Companion", "app_debug"})
public final class EncryptedPayload {
    @org.jetbrains.annotations.NotNull()
    private final byte[] nonce = null;
    @org.jetbrains.annotations.NotNull()
    private final byte[] ciphertextAndTag = null;
    private static final int MAX_NONCE_BYTES = 32;
    private static final int MAX_CIPHERTEXT_BYTES = 65536;
    @org.jetbrains.annotations.NotNull()
    public static final com.ghostchat.crypto.EncryptedPayload.Companion Companion = null;
    
    public EncryptedPayload(@org.jetbrains.annotations.NotNull()
    byte[] nonce, @org.jetbrains.annotations.NotNull()
    byte[] ciphertextAndTag) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] getNonce() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] getCiphertextAndTag() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] toWireBytes() {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ghostchat.crypto.EncryptedPayload copy(@org.jetbrains.annotations.NotNull()
    byte[] nonce, @org.jetbrains.annotations.NotNull()
    byte[] ciphertextAndTag) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/ghostchat/crypto/EncryptedPayload$Companion;", "", "()V", "MAX_CIPHERTEXT_BYTES", "", "MAX_NONCE_BYTES", "fromWireBytes", "Lcom/ghostchat/crypto/EncryptedPayload;", "bytes", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.ghostchat.crypto.EncryptedPayload fromWireBytes(@org.jetbrains.annotations.NotNull()
        byte[] bytes) {
            return null;
        }
    }
}