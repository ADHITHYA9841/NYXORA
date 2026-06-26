package com.ghostchat.domain.model

data class ChatMessage(
    val id: Long,
    val text: String,
    val direction: MessageDirection,
    val timestampMillis: Long,
)

enum class MessageDirection {
    Sent,
    Received,
}
