package com.ghostchat.crypto;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlinx.coroutines.flow.StateFlow;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\u0011H\u0002J\u0016\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0016J*\u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\u0004\b\u0000\u0010\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u0002H\u00180\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001cR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/ghostchat/crypto/SessionKeyManager;", "", "secureMemoryCleaner", "Lcom/ghostchat/crypto/SecureMemoryCleaner;", "(Lcom/ghostchat/crypto/SecureMemoryCleaner;)V", "_hasSessionKey", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "hasSessionKey", "Lkotlinx/coroutines/flow/StateFlow;", "getHasSessionKey", "()Lkotlinx/coroutines/flow/StateFlow;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "sessionKeyBytes", "", "destroySessionKey", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "destroySessionKeyLocked", "replaceSessionKey", "newKeyBytes", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "useSessionKey", "T", "block", "Lkotlin/Function1;", "Ljavax/crypto/SecretKey;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class SessionKeyManager {
    @org.jetbrains.annotations.NotNull()
    private final com.ghostchat.crypto.SecureMemoryCleaner secureMemoryCleaner = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.sync.Mutex mutex = null;
    @org.jetbrains.annotations.Nullable()
    private byte[] sessionKeyBytes;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _hasSessionKey = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> hasSessionKey = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String AES_ALGORITHM = "AES";
    @org.jetbrains.annotations.NotNull()
    public static final com.ghostchat.crypto.SessionKeyManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public SessionKeyManager(@org.jetbrains.annotations.NotNull()
    com.ghostchat.crypto.SecureMemoryCleaner secureMemoryCleaner) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getHasSessionKey() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object replaceSessionKey(@org.jetbrains.annotations.NotNull()
    byte[] newKeyBytes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final <T extends java.lang.Object>java.lang.Object useSessionKey(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super javax.crypto.SecretKey, ? extends T> block, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super T> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object destroySessionKey(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void destroySessionKeyLocked() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/ghostchat/crypto/SessionKeyManager$Companion;", "", "()V", "AES_ALGORITHM", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}