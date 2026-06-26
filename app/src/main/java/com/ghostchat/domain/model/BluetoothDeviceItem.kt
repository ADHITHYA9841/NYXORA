package com.ghostchat.domain.model

data class BluetoothDeviceItem(
    val address: String,
    val name: String,
    val bonded: Boolean,
)
