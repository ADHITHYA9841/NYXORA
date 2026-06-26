package com.ghostchat.domain.model

data class SecuritySettings(
    val biometricLockEnabled: Boolean = false,
    val autoDeleteMinutes: Int = 10,
    val panicModeEnabled: Boolean = true,
)
