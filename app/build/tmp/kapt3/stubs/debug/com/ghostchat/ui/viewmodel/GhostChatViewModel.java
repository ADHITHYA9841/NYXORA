package com.ghostchat.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import com.ghostchat.domain.model.BluetoothDeviceItem;
import com.ghostchat.repository.GhostChatRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000bJ\b\u0010\u0010\u001a\u00020\u000bH\u0014J\u0006\u0010\u0011\u001a\u00020\u000bJ\u0006\u0010\u0012\u001a\u00020\u000bJ\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001d\u001a\u00020\u000bJ\u0006\u0010\u001e\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u001f"}, d2 = {"Lcom/ghostchat/ui/viewmodel/GhostChatViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/ghostchat/repository/GhostChatRepository;", "(Lcom/ghostchat/repository/GhostChatRepository;)V", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/ghostchat/domain/model/GhostChatUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearTransientError", "", "connect", "device", "Lcom/ghostchat/domain/model/BluetoothDeviceItem;", "disconnect", "onCleared", "panic", "reconnect", "sendMessage", "message", "", "setAutoDeleteMinutes", "minutes", "", "setBiometricLockEnabled", "enabled", "", "setPanicModeEnabled", "startDiscovery", "stopDiscovery", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class GhostChatViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ghostchat.repository.GhostChatRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ghostchat.domain.model.GhostChatUiState> uiState = null;
    
    @javax.inject.Inject()
    public GhostChatViewModel(@org.jetbrains.annotations.NotNull()
    com.ghostchat.repository.GhostChatRepository repository) {
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
    
    public final void connect(@org.jetbrains.annotations.NotNull()
    com.ghostchat.domain.model.BluetoothDeviceItem device) {
    }
    
    public final void reconnect() {
    }
    
    public final void sendMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public final void disconnect() {
    }
    
    public final void panic() {
    }
    
    public final void setBiometricLockEnabled(boolean enabled) {
    }
    
    public final void setPanicModeEnabled(boolean enabled) {
    }
    
    public final void setAutoDeleteMinutes(int minutes) {
    }
    
    public final void clearTransientError() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}