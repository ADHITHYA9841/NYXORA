package com.ghostchat.bluetooth;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.ghostchat.domain.model.BluetoothDeviceItem;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a0\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 R2\u00020\u0001:\u0001RB\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020(H\u0002J\b\u0010,\u001a\u00020(H\u0002J\u0016\u0010-\u001a\u00020(2\u0006\u0010.\u001a\u00020/H\u0086@\u00a2\u0006\u0002\u00100J\u001a\u00101\u001a\u00020(2\n\b\u0002\u00102\u001a\u0004\u0018\u00010/H\u0086@\u00a2\u0006\u0002\u00100J\u0010\u00103\u001a\u00020(2\u0006\u00104\u001a\u00020/H\u0002J\u0016\u00105\u001a\u00020(2\u0006\u00106\u001a\u00020\u000fH\u0083@\u00a2\u0006\u0002\u00107J\b\u00108\u001a\u00020\u001cH\u0002J\b\u00109\u001a\u00020\u001cH\u0002J\u0010\u0010:\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020/H\u0002J\u000e\u0010<\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0003J\u0016\u0010=\u001a\u00020(2\u0006\u0010>\u001a\u00020?H\u0082@\u00a2\u0006\u0002\u0010@J\b\u0010A\u001a\u00020(H\u0002J\u0006\u0010B\u001a\u00020(J\n\u0010C\u001a\u0004\u0018\u00010\u0011H\u0002J\u0016\u0010D\u001a\u00020(2\u0006\u0010E\u001a\u00020FH\u0086@\u00a2\u0006\u0002\u0010GJ\u000e\u0010H\u001a\u00020(H\u0082@\u00a2\u0006\u0002\u0010IJ\u0006\u0010J\u001a\u00020(J\u0006\u0010K\u001a\u00020(J\u0006\u0010L\u001a\u00020(J\b\u0010M\u001a\u00020(H\u0002J\u000e\u0010N\u001a\u0004\u0018\u00010**\u00020OH\u0002J\f\u0010P\u001a\u00020/*\u00020*H\u0003J\u000e\u0010Q\u001a\u0004\u0018\u00010\b*\u00020*H\u0003R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n \u0014*\u0004\u0018\u00010\u00130\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006S"}, d2 = {"Lcom/ghostchat/bluetooth/ClassicBluetoothController;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_devices", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/ghostchat/domain/model/BluetoothDeviceItem;", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/ghostchat/bluetooth/BluetoothEvent;", "acceptSocket", "Landroid/bluetooth/BluetoothServerSocket;", "activeSocket", "Landroid/bluetooth/BluetoothSocket;", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "bluetoothManager", "Landroid/bluetooth/BluetoothManager;", "kotlin.jvm.PlatformType", "devices", "Lkotlinx/coroutines/flow/StateFlow;", "getDevices", "()Lkotlinx/coroutines/flow/StateFlow;", "discoveryReceiver", "Landroid/content/BroadcastReceiver;", "discoveryReceiverRegistered", "", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "outputStream", "Ljava/io/DataOutputStream;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "writeMutex", "Lkotlinx/coroutines/sync/Mutex;", "addDevice", "", "device", "Landroid/bluetooth/BluetoothDevice;", "closeAcceptSocket", "closeActiveSocket", "connect", "address", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnect", "reason", "emitError", "message", "handleConnectedSocket", "socket", "(Landroid/bluetooth/BluetoothSocket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasConnectPermission", "hasDiscoveryPermission", "hasPermission", "permission", "pairedDevices", "readLoop", "inputStream", "Ljava/io/DataInputStream;", "(Ljava/io/DataInputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerDiscoveryReceiver", "release", "requireBluetoothAdapter", "sendFrame", "frame", "Lcom/ghostchat/bluetooth/BluetoothFrame;", "(Lcom/ghostchat/bluetooth/BluetoothFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSessionEndIfPossible", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startDiscovery", "startServer", "stopDiscovery", "unregisterDiscoveryReceiver", "bluetoothDeviceExtra", "Landroid/content/Intent;", "safeName", "toItem", "Companion", "app_debug"})
public final class ClassicBluetoothController {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final android.bluetooth.BluetoothManager bluetoothManager = null;
    @org.jetbrains.annotations.Nullable()
    private final android.bluetooth.BluetoothAdapter bluetoothAdapter = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.sync.Mutex writeMutex = null;
    private boolean discoveryReceiverRegistered = false;
    @org.jetbrains.annotations.Nullable()
    private android.bluetooth.BluetoothServerSocket acceptSocket;
    @org.jetbrains.annotations.Nullable()
    private android.bluetooth.BluetoothSocket activeSocket;
    @org.jetbrains.annotations.Nullable()
    private java.io.DataOutputStream outputStream;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.ghostchat.domain.model.BluetoothDeviceItem>> _devices = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.ghostchat.domain.model.BluetoothDeviceItem>> devices = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.ghostchat.bluetooth.BluetoothEvent> _events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.ghostchat.bluetooth.BluetoothEvent> events = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.BroadcastReceiver discoveryReceiver = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String APP_NAME = "GhostChat";
    @org.jetbrains.annotations.NotNull()
    private static final java.util.UUID SERVICE_UUID = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.ghostchat.bluetooth.ClassicBluetoothController.Companion Companion = null;
    
    @javax.inject.Inject()
    public ClassicBluetoothController(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.ghostchat.domain.model.BluetoothDeviceItem>> getDevices() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.ghostchat.bluetooth.BluetoothEvent> getEvents() {
        return null;
    }
    
    public final void startDiscovery() {
    }
    
    public final void stopDiscovery() {
    }
    
    public final void startServer() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object connect(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendFrame(@org.jetbrains.annotations.NotNull()
    com.ghostchat.bluetooth.BluetoothFrame frame, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object disconnect(@org.jetbrains.annotations.Nullable()
    java.lang.String reason, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void release() {
    }
    
    private final java.lang.Object sendSessionEndIfPossible(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final java.lang.Object handleConnectedSocket(android.bluetooth.BluetoothSocket socket, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object readLoop(java.io.DataInputStream inputStream, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void registerDiscoveryReceiver() {
    }
    
    private final void unregisterDiscoveryReceiver() {
    }
    
    private final void addDevice(android.bluetooth.BluetoothDevice device) {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final java.util.List<com.ghostchat.domain.model.BluetoothDeviceItem> pairedDevices() {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final com.ghostchat.domain.model.BluetoothDeviceItem toItem(android.bluetooth.BluetoothDevice $this$toItem) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final java.lang.String safeName(android.bluetooth.BluetoothDevice $this$safeName) {
        return null;
    }
    
    private final android.bluetooth.BluetoothAdapter requireBluetoothAdapter() {
        return null;
    }
    
    private final void emitError(java.lang.String message) {
    }
    
    private final boolean hasDiscoveryPermission() {
        return false;
    }
    
    private final boolean hasConnectPermission() {
        return false;
    }
    
    private final boolean hasPermission(java.lang.String permission) {
        return false;
    }
    
    private final void closeActiveSocket() {
    }
    
    private final void closeAcceptSocket() {
    }
    
    private final android.bluetooth.BluetoothDevice bluetoothDeviceExtra(android.content.Intent $this$bluetoothDeviceExtra) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/ghostchat/bluetooth/ClassicBluetoothController$Companion;", "", "()V", "APP_NAME", "", "SERVICE_UUID", "Ljava/util/UUID;", "getSERVICE_UUID", "()Ljava/util/UUID;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.UUID getSERVICE_UUID() {
            return null;
        }
    }
}