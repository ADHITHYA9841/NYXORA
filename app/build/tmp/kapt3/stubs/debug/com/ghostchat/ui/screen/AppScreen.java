package com.ghostchat.ui.screen;

import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import com.ghostchat.domain.model.ConnectionStatus;
import com.ghostchat.security.BiometricAuthManager;
import com.ghostchat.ui.viewmodel.GhostChatViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/ghostchat/ui/screen/AppScreen;", "", "(Ljava/lang/String;I)V", "Splash", "Discovery", "Chat", "Settings", "app_debug"})
enum AppScreen {
    /*public static final*/ Splash /* = new Splash() */,
    /*public static final*/ Discovery /* = new Discovery() */,
    /*public static final*/ Chat /* = new Chat() */,
    /*public static final*/ Settings /* = new Settings() */;
    
    AppScreen() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.ghostchat.ui.screen.AppScreen> getEntries() {
        return null;
    }
}