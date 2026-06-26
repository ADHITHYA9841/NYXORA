package com.ghostchat.bluetooth

sealed interface BluetoothFrame {
    val payload: ByteArray

    data class PublicKey(override val payload: ByteArray) : BluetoothFrame
    data class EncryptedMessage(override val payload: ByteArray) : BluetoothFrame
    data object SessionEnd : BluetoothFrame {
        override val payload: ByteArray = ByteArray(0)
    }

    companion object {
        const val TYPE_PUBLIC_KEY = 1
        const val TYPE_ENCRYPTED_MESSAGE = 2
        const val TYPE_SESSION_END = 3
        const val MAX_FRAME_BYTES = 64 * 1024

        fun from(type: Int, payload: ByteArray): BluetoothFrame =
            when (type) {
                TYPE_PUBLIC_KEY -> PublicKey(payload)
                TYPE_ENCRYPTED_MESSAGE -> EncryptedMessage(payload)
                TYPE_SESSION_END -> SessionEnd
                else -> throw IllegalArgumentException("Unknown Bluetooth frame type: $type")
            }

        fun typeOf(frame: BluetoothFrame): Int =
            when (frame) {
                is PublicKey -> TYPE_PUBLIC_KEY
                is EncryptedMessage -> TYPE_ENCRYPTED_MESSAGE
                SessionEnd -> TYPE_SESSION_END
            }
    }
}
