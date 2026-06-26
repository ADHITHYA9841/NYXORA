package com.ghostchat.domain.model

sealed interface ConnectionStatus {
    data object Idle : ConnectionStatus
    data object Scanning : ConnectionStatus
    data class Connecting(val deviceName: String) : ConnectionStatus
    data class Connected(
        val deviceName: String,
        val encrypted: Boolean,
    ) : ConnectionStatus
    data class Disconnected(val reason: String? = null) : ConnectionStatus
    data class Error(val message: String) : ConnectionStatus
}
