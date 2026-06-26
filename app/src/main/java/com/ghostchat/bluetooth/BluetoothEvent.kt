package com.ghostchat.bluetooth

sealed interface BluetoothEvent {
    data class Connected(val deviceName: String, val address: String) : BluetoothEvent
    data class FrameReceived(val frame: BluetoothFrame) : BluetoothEvent
    data class Disconnected(val reason: String? = null) : BluetoothEvent
    data class Error(val message: String) : BluetoothEvent
}
