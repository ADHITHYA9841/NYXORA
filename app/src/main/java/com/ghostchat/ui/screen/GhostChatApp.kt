package com.ghostchat.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ghostchat.domain.model.ConnectionStatus
import com.ghostchat.security.BiometricAuthManager
import com.ghostchat.ui.viewmodel.GhostChatViewModel
import kotlinx.coroutines.launch

private enum class AppScreen {
    Splash,
    Discovery,
    Chat,
    Settings,
}

@Composable
fun GhostChatApp(
    viewModel: GhostChatViewModel,
    biometricAuthManager: BiometricAuthManager,
) {
    val state by viewModel.uiState.collectAsState()
    var screen by remember { mutableStateOf(AppScreen.Splash) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(state.connectionStatus) {
        when (state.connectionStatus) {
            is ConnectionStatus.Connected -> screen = AppScreen.Chat
            is ConnectionStatus.Disconnected -> {
                if (screen == AppScreen.Chat) {
                    screen = AppScreen.Discovery
                }
            }
            else -> Unit
        }
    }

    LaunchedEffect(state.errorMessage) {
        val message = state.errorMessage ?: return@LaunchedEffect
        snackbarHostState.showSnackbar(message)
        viewModel.clearTransientError()
    }

    Box(Modifier.fillMaxSize()) {
        when (screen) {
            AppScreen.Splash -> SplashScreen(
                onFinished = { screen = AppScreen.Discovery },
            )

            AppScreen.Discovery -> DeviceDiscoveryScreen(
                state = state,
                onStartScan = viewModel::startDiscovery,
                onStopScan = viewModel::stopDiscovery,
                onConnect = viewModel::connect,
                onReconnect = viewModel::reconnect,
                onOpenSettings = { screen = AppScreen.Settings },
            )

            AppScreen.Chat -> ChatScreen(
                state = state,
                onSendMessage = viewModel::sendMessage,
                onDisconnect = {
                    viewModel.disconnect()
                    screen = AppScreen.Discovery
                },
                onPanic = {
                    viewModel.panic()
                    screen = AppScreen.Discovery
                },
                onOpenSettings = { screen = AppScreen.Settings },
            )

            AppScreen.Settings -> SecuritySettingsScreen(
                settings = state.settings,
                onBack = {
                    screen = if (state.connectionStatus is ConnectionStatus.Connected) {
                        AppScreen.Chat
                    } else {
                        AppScreen.Discovery
                    }
                },
                onBiometricChange = { enabled ->
                    if (!enabled) {
                        viewModel.setBiometricLockEnabled(false)
                    } else {
                        biometricAuthManager.authenticate(
                            onSuccess = { viewModel.setBiometricLockEnabled(true) },
                            onError = { error ->
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(error)
                                }
                            },
                        )
                    }
                },
                onAutoDeleteMinutesChange = viewModel::setAutoDeleteMinutes,
                onPanicModeChange = viewModel::setPanicModeEnabled,
            )
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}
