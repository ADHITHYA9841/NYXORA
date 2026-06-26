package com.ghostchat.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.ghostchat.domain.model.ChatMessage
import com.ghostchat.domain.model.ConnectionStatus
import com.ghostchat.domain.model.GhostChatUiState
import com.ghostchat.domain.model.MessageDirection
import com.ghostchat.ui.components.EncryptionIndicator
import com.ghostchat.utils.TimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    state: GhostChatUiState,
    onSendMessage: (String) -> Unit,
    onDisconnect: () -> Unit,
    onPanic: () -> Unit,
    onOpenSettings: () -> Unit,
) {
    var draft by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val connected = state.connectionStatus as? ConnectionStatus.Connected

    LaunchedEffect(state.messages.size) {
        if (state.messages.isNotEmpty()) {
            listState.animateScrollToItem(state.messages.lastIndex)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(state.activeDeviceName ?: connected?.deviceName ?: "Secure chat")
                        EncryptionIndicator(encrypted = state.encryptionReady)
                    }
                },
                actions = {
                    IconButton(onClick = onOpenSettings) {
                        Icon(Icons.Rounded.Settings, contentDescription = "Security settings")
                    }
                    IconButton(onClick = onDisconnect) {
                        Icon(Icons.Rounded.Close, contentDescription = "Disconnect")
                    }
                },
            )
        },
        bottomBar = {
            ChatInputBar(
                draft = draft,
                onDraftChange = { draft = it },
                onSend = {
                    val message = draft
                    if (message.isNotBlank()) {
                        onSendMessage(message)
                        draft = ""
                    }
                },
                onPanic = onPanic,
                panicEnabled = state.settings.panicModeEnabled,
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            if (state.messages.isEmpty()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(32.dp),
                    text = if (state.encryptionReady) {
                        "Encrypted session ready."
                    } else {
                        "Establishing secure session."
                    },
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 18.dp),
            ) {
                items(
                    items = state.messages,
                    key = { it.id },
                ) { message ->
                    MessageBubble(message = message)
                }
            }
        }
    }
}

@Composable
private fun MessageBubble(message: ChatMessage) {
    val sent = message.direction == MessageDirection.Sent
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (sent) Arrangement.End else Arrangement.Start,
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(0.82f),
            shape = RoundedCornerShape(8.dp),
            color = if (sent) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
            contentColor = if (sent) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = TimeFormatter.shortTime(message.timestampMillis),
                    style = MaterialTheme.typography.labelSmall,
                    color = if (sent) {
                        MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.78f)
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.78f)
                    },
                )
            }
        }
    }
}

@Composable
private fun ChatInputBar(
    draft: String,
    onDraftChange: (String) -> Unit,
    onSend: () -> Unit,
    onPanic: () -> Unit,
    panicEnabled: Boolean,
) {
    Surface(
        tonalElevation = 3.dp,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .imePadding()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = draft,
                onValueChange = onDraftChange,
                placeholder = { Text("Message") },
                maxLines = 4,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Send,
                ),
                keyboardActions = KeyboardActions(onSend = { onSend() }),
            )
            if (panicEnabled) {
                FilledTonalIconButton(onClick = onPanic) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Panic mode",
                        tint = MaterialTheme.colorScheme.error,
                    )
                }
            }
            FilledTonalIconButton(
                onClick = onSend,
                enabled = draft.isNotBlank(),
            ) {
                Icon(Icons.AutoMirrored.Rounded.Send, contentDescription = "Send encrypted message")
            }
        }
    }
}
