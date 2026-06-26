package com.ghostchat.ui.screen;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.core.content.ContextCompat;
import com.ghostchat.domain.model.BluetoothDeviceItem;
import com.ghostchat.domain.model.ConnectionStatus;
import com.ghostchat.domain.model.GhostChatUiState;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\\\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u001e\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\t2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u0013\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002\u00a2\u0006\u0002\u0010\u0011\u001a\u0014\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0010H\u0002\u00a8\u0006\u0016"}, d2 = {"DeviceDiscoveryScreen", "", "state", "Lcom/ghostchat/domain/model/GhostChatUiState;", "onStartScan", "Lkotlin/Function0;", "onStopScan", "onConnect", "Lkotlin/Function1;", "Lcom/ghostchat/domain/model/BluetoothDeviceItem;", "onReconnect", "onOpenSettings", "DeviceRow", "device", "requiredBluetoothPermissions", "", "", "()[Ljava/lang/String;", "hasPermission", "", "Landroid/content/Context;", "permission", "app_debug"})
public final class DeviceDiscoveryScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DeviceDiscoveryScreen(@org.jetbrains.annotations.NotNull()
    com.ghostchat.domain.model.GhostChatUiState state, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onStartScan, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onStopScan, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.ghostchat.domain.model.BluetoothDeviceItem, kotlin.Unit> onConnect, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onReconnect, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenSettings) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DeviceRow(com.ghostchat.domain.model.BluetoothDeviceItem device, kotlin.jvm.functions.Function0<kotlin.Unit> onConnect) {
    }
    
    private static final java.lang.String[] requiredBluetoothPermissions() {
        return null;
    }
    
    private static final boolean hasPermission(android.content.Context $this$hasPermission, java.lang.String permission) {
        return false;
    }
}