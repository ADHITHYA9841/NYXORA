package com.ghostchat.security

import android.view.Window
import android.view.WindowManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScreenSecurityManager @Inject constructor() {
    fun enableScreenshotProtection(window: Window) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE,
        )
    }
}
