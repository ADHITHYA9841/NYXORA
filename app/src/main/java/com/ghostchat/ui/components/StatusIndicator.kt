package com.ghostchat.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.LockOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ghostchat.domain.model.ConnectionStatus

@Composable
fun ConnectionIndicator(
    status: ConnectionStatus,
    modifier: Modifier = Modifier,
) {
    val (label, color) = when (status) {
        ConnectionStatus.Idle -> "Ready" to MaterialTheme.colorScheme.outline
        ConnectionStatus.Scanning -> "Scanning" to MaterialTheme.colorScheme.tertiary
        is ConnectionStatus.Connecting -> "Connecting" to MaterialTheme.colorScheme.tertiary
        is ConnectionStatus.Connected -> if (status.encrypted) {
            "Encrypted" to MaterialTheme.colorScheme.primary
        } else {
            "Key exchange" to MaterialTheme.colorScheme.tertiary
        }
        is ConnectionStatus.Disconnected -> "Disconnected" to MaterialTheme.colorScheme.error
        is ConnectionStatus.Error -> "Error" to MaterialTheme.colorScheme.error
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StatusDot(color = color)
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun EncryptionIndicator(
    encrypted: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = if (encrypted) Icons.Rounded.Lock else Icons.Rounded.LockOpen,
            contentDescription = if (encrypted) "Encrypted" else "Encryption pending",
            tint = if (encrypted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
        )
        Text(
            text = if (encrypted) "AES-256-GCM" else "ECDH pending",
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Composable
private fun StatusDot(color: Color) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .size(9.dp)
            .clip(CircleShape)
            .background(color),
    )
}
