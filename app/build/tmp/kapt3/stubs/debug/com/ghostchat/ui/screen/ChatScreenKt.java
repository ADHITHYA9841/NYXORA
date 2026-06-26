package com.ghostchat.ui.screen;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import com.ghostchat.domain.model.ChatMessage;
import com.ghostchat.domain.model.ConnectionStatus;
import com.ghostchat.domain.model.GhostChatUiState;
import com.ghostchat.domain.model.MessageDirection;
import com.ghostchat.utils.TimeFormatter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001aH\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u0003\u001aN\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a\u0010\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0003\u00a8\u0006\u0014"}, d2 = {"ChatInputBar", "", "draft", "", "onDraftChange", "Lkotlin/Function1;", "onSend", "Lkotlin/Function0;", "onPanic", "panicEnabled", "", "ChatScreen", "state", "Lcom/ghostchat/domain/model/GhostChatUiState;", "onSendMessage", "onDisconnect", "onOpenSettings", "MessageBubble", "message", "Lcom/ghostchat/domain/model/ChatMessage;", "app_debug"})
public final class ChatScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ChatScreen(@org.jetbrains.annotations.NotNull()
    com.ghostchat.domain.model.GhostChatUiState state, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSendMessage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDisconnect, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPanic, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenSettings) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MessageBubble(com.ghostchat.domain.model.ChatMessage message) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ChatInputBar(java.lang.String draft, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDraftChange, kotlin.jvm.functions.Function0<kotlin.Unit> onSend, kotlin.jvm.functions.Function0<kotlin.Unit> onPanic, boolean panicEnabled) {
    }
}