package com.ghostchat.ui.screen;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import com.ghostchat.domain.model.SecuritySettings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001aZ\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001aG\u0010\u000f\u001a\u00020\u00012\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\n\u00a2\u0006\u0002\b\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\f2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u00a8\u0006\u0017"}, d2 = {"AutoDeleteCard", "", "minutes", "", "onMinutesChange", "Lkotlin/Function1;", "SecuritySettingsScreen", "settings", "Lcom/ghostchat/domain/model/SecuritySettings;", "onBack", "Lkotlin/Function0;", "onBiometricChange", "", "onAutoDeleteMinutesChange", "onPanicModeChange", "SettingToggleRow", "icon", "Landroidx/compose/runtime/Composable;", "title", "", "subtitle", "checked", "onCheckedChange", "app_debug"})
public final class SecuritySettingsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SecuritySettingsScreen(@org.jetbrains.annotations.NotNull()
    com.ghostchat.domain.model.SecuritySettings settings, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onBiometricChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onAutoDeleteMinutesChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onPanicModeChange) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SettingToggleRow(kotlin.jvm.functions.Function0<kotlin.Unit> icon, java.lang.String title, java.lang.String subtitle, boolean checked, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onCheckedChange) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AutoDeleteCard(int minutes, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onMinutesChange) {
    }
}