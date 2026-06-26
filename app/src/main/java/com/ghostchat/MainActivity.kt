package com.ghostchat

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.ghostchat.security.BiometricAuthManager
import com.ghostchat.security.ScreenSecurityManager
import com.ghostchat.ui.screen.GhostChatApp
import com.ghostchat.ui.theme.GhostChatTheme
import com.ghostchat.ui.viewmodel.GhostChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.fragment.app.FragmentActivity

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @Inject lateinit var screenSecurityManager: ScreenSecurityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenSecurityManager.enableScreenshotProtection(window)

        val viewModel = ViewModelProvider(this)[GhostChatViewModel::class.java]
        val biometricAuthManager = BiometricAuthManager(this)

        setContent {
            GhostChatTheme {
                GhostChatApp(
                    viewModel = viewModel,
                    biometricAuthManager = biometricAuthManager,
                )
            }
        }
    }
}
