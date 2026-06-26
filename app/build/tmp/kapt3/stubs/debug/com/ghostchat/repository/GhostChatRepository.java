package com.ghostchat.repository;

import com.ghostchat.bluetooth.BluetoothEvent;
import com.ghostchat.bluetooth.BluetoothFrame;
import com.ghostchat.bluetooth.ClassicBluetoothController;
import com.ghostchat.crypto.AESGCMEncryptionManager;
import com.ghostchat.crypto.ECDHKeyExchangeManager;
import com.ghostchat.crypto.EncryptedPayload;
import com.ghostchat.crypto.KeyExchangeMaterial;
import com.ghostchat.crypto.SecureMemoryCleaner;
import com.ghostchat.crypto.SessionKeyManager;
import com.ghostchat.domain.model.BluetoothDeviceItem;
import com.ghostchat.domain.model.ChatMessage;
import com.ghostchat.domain.model.ConnectionStatus;
import com.ghostchat.domain.model.GhostChatUiState;
import com.ghostchat.domain.model.MessageDirection;
import javax.crypto.AEADBadTagException;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u0000 M2\u00020\u0001:\u0001MB/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u000e\u0010$\u001a\u00020\u001fH\u0082@\u00a2\u0006\u0002\u0010%J\b\u0010&\u001a\u00020\u001fH\u0002J\u0006\u0010\'\u001a\u00020\u001fJ\u0016\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010*J\u000e\u0010+\u001a\u00020\u001fH\u0082@\u00a2\u0006\u0002\u0010%J\u0016\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020!H\u0082@\u00a2\u0006\u0002\u0010.J\u000e\u0010/\u001a\u00020\u001fH\u0086@\u00a2\u0006\u0002\u0010%J\u0016\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u000202H\u0082@\u00a2\u0006\u0002\u00103J\u0016\u00104\u001a\u00020\u001f2\u0006\u00105\u001a\u000206H\u0082@\u00a2\u0006\u0002\u00107J\u0016\u00108\u001a\u00020\u001f2\u0006\u00109\u001a\u00020:H\u0082@\u00a2\u0006\u0002\u0010;J\u0016\u0010<\u001a\u00020\u001f2\u0006\u0010=\u001a\u000206H\u0082@\u00a2\u0006\u0002\u00107J\u000e\u0010>\u001a\u00020\u001fH\u0086@\u00a2\u0006\u0002\u0010%J\u000e\u0010?\u001a\u00020\u001fH\u0086@\u00a2\u0006\u0002\u0010%J\b\u0010@\u001a\u00020\u001fH\u0002J\u0016\u0010A\u001a\u00020\u001f2\u0006\u0010B\u001a\u00020!H\u0086@\u00a2\u0006\u0002\u0010.J\u000e\u0010C\u001a\u00020\u001f2\u0006\u0010D\u001a\u00020EJ\u000e\u0010F\u001a\u00020\u001f2\u0006\u0010G\u001a\u00020HJ\u000e\u0010I\u001a\u00020\u001f2\u0006\u0010G\u001a\u00020HJ\u0006\u0010J\u001a\u00020\u001fJ\u0006\u0010K\u001a\u00020\u001fJ\u0006\u0010L\u001a\u00020\u001fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006N"}, d2 = {"Lcom/ghostchat/repository/GhostChatRepository;", "", "bluetoothController", "Lcom/ghostchat/bluetooth/ClassicBluetoothController;", "keyExchangeManager", "Lcom/ghostchat/crypto/ECDHKeyExchangeManager;", "encryptionManager", "Lcom/ghostchat/crypto/AESGCMEncryptionManager;", "sessionKeyManager", "Lcom/ghostchat/crypto/SessionKeyManager;", "secureMemoryCleaner", "Lcom/ghostchat/crypto/SecureMemoryCleaner;", "(Lcom/ghostchat/bluetooth/ClassicBluetoothController;Lcom/ghostchat/crypto/ECDHKeyExchangeManager;Lcom/ghostchat/crypto/AESGCMEncryptionManager;Lcom/ghostchat/crypto/SessionKeyManager;Lcom/ghostchat/crypto/SecureMemoryCleaner;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/ghostchat/domain/model/GhostChatUiState;", "autoDeleteJob", "Lkotlinx/coroutines/Job;", "keyExchangeMaterial", "Lcom/ghostchat/crypto/KeyExchangeMaterial;", "lastConnectedDevice", "Lcom/ghostchat/domain/model/BluetoothDeviceItem;", "messageIdCounter", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addMessage", "", "text", "", "direction", "Lcom/ghostchat/domain/model/MessageDirection;", "beginKeyExchange", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearKeyExchangeMaterial", "clearTransientError", "connect", "device", "(Lcom/ghostchat/domain/model/BluetoothDeviceItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "destroySensitiveStateOnly", "destroySession", "reason", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnect", "handleBluetoothEvent", "event", "Lcom/ghostchat/bluetooth/BluetoothEvent;", "(Lcom/ghostchat/bluetooth/BluetoothEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleEncryptedMessage", "payloadBytes", "", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleFrame", "frame", "Lcom/ghostchat/bluetooth/BluetoothFrame;", "(Lcom/ghostchat/bluetooth/BluetoothFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handlePeerPublicKey", "peerPublicKey", "panic", "reconnect", "scheduleAutoDelete", "sendText", "message", "setAutoDeleteMinutes", "minutes", "", "setBiometricLockEnabled", "enabled", "", "setPanicModeEnabled", "shutdown", "startDiscovery", "stopDiscovery", "Companion", "app_debug"})
public final class GhostChatRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ghostchat.bluetooth.ClassicBluetoothController bluetoothController = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ghostchat.crypto.ECDHKeyExchangeManager keyExchangeManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ghostchat.crypto.AESGCMEncryptionManager encryptionManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ghostchat.crypto.SessionKeyManager sessionKeyManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ghostchat.crypto.SecureMemoryCleaner secureMemoryCleaner = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.ghostchat.domain.model.GhostChatUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ghostchat.domain.model.GhostChatUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private com.ghostchat.crypto.KeyExchangeMaterial keyExchangeMaterial;
    private long messageIdCounter = 0L;
    @org.jetbrains.annotations.Nullable()
    private com.ghostchat.domain.model.BluetoothDeviceItem lastConnectedDevice;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job autoDeleteJob;
    private static final int MIN_AUTO_DELETE_MINUTES = 1;
    private static final int MAX_AUTO_DELETE_MINUTES = 60;
    @org.jetbrains.annotations.NotNull()
    public static final com.ghostchat.repository.GhostChatRepository.Companion Companion = null;
    
    @javax.inject.Inject()
    public GhostChatRepository(@org.jetbrains.annotations.NotNull()
    com.ghostchat.bluetooth.ClassicBluetoothController bluetoothController, @org.jetbrains.annotations.NotNull()
    com.ghostchat.crypto.ECDHKeyExchangeManager keyExchangeManager, @org.jetbrains.annotations.NotNull()
    com.ghostchat.crypto.AESGCMEncryptionManager encryptionManager, @org.jetbrains.annotations.NotNull()
    com.ghostchat.crypto.SessionKeyManager sessionKeyManager, @org.jetbrains.annotations.NotNull()
    com.ghostchat.crypto.SecureMemoryCleaner secureMemoryCleaner) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.ghostchat.domain.model.GhostChatUiState> getUiState() {
        return null;
    }
    
    public final void startDiscovery() {
    }
    
    public final void stopDiscovery() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object connect(@org.jetbrains.annotations.NotNull()
    com.ghostchat.domain.model.BluetoothDeviceItem device, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object reconnect(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendText(@org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object disconnect(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object panic(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void setBiometricLockEnabled(boolean enabled) {
    }
    
    public final void setPanicModeEnabled(boolean enabled) {
    }
    
    public final void setAutoDeleteMinutes(int minutes) {
    }
    
    public final void clearTransientError() {
    }
    
    public final void shutdown() {
    }
    
    private final java.lang.Object handleBluetoothEvent(com.ghostchat.bluetooth.BluetoothEvent event, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object beginKeyExchange(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object handleFrame(com.ghostchat.bluetooth.BluetoothFrame frame, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object handlePeerPublicKey(byte[] peerPublicKey, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object handleEncryptedMessage(byte[] payloadBytes, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void addMessage(java.lang.String text, com.ghostchat.domain.model.MessageDirection direction) {
    }
    
    private final void scheduleAutoDelete() {
    }
    
    private final java.lang.Object destroySession(java.lang.String reason, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object destroySensitiveStateOnly(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void clearKeyExchangeMaterial() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/ghostchat/repository/GhostChatRepository$Companion;", "", "()V", "MAX_AUTO_DELETE_MINUTES", "", "MIN_AUTO_DELETE_MINUTES", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}