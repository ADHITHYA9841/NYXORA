package com.ghostchat.domain.model

data class GhostChatUiState(
    val devices: List<BluetoothDeviceItem> = emptyList(),
    val messages: List<ChatMessage> = emptyList(),
    val connectionStatus: ConnectionStatus = ConnectionStatus.Idle,
    val settings: SecuritySettings = SecuritySettings(),
    val encryptionReady: Boolean = false,
    val activeDeviceName: String? = null,
    val errorMessage: String? = null,
)
