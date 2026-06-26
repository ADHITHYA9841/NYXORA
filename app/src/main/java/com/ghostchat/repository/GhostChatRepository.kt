package com.ghostchat.repository

import com.ghostchat.bluetooth.BluetoothEvent
import com.ghostchat.bluetooth.BluetoothFrame
import com.ghostchat.bluetooth.ClassicBluetoothController
import com.ghostchat.crypto.AESGCMEncryptionManager
import com.ghostchat.crypto.ECDHKeyExchangeManager
import com.ghostchat.crypto.EncryptedPayload
import com.ghostchat.crypto.KeyExchangeMaterial
import com.ghostchat.crypto.SecureMemoryCleaner
import com.ghostchat.crypto.SessionKeyManager
import com.ghostchat.domain.model.BluetoothDeviceItem
import com.ghostchat.domain.model.ChatMessage
import com.ghostchat.domain.model.ConnectionStatus
import com.ghostchat.domain.model.GhostChatUiState
import com.ghostchat.domain.model.MessageDirection
import javax.crypto.AEADBadTagException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Singleton
class GhostChatRepository @Inject constructor(
    private val bluetoothController: ClassicBluetoothController,
    private val keyExchangeManager: ECDHKeyExchangeManager,
    private val encryptionManager: AESGCMEncryptionManager,
    private val sessionKeyManager: SessionKeyManager,
    private val secureMemoryCleaner: SecureMemoryCleaner,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _uiState = MutableStateFlow(GhostChatUiState())
    val uiState: StateFlow<GhostChatUiState> = _uiState

    private var keyExchangeMaterial: KeyExchangeMaterial? = null
    private var messageIdCounter = 0L
    private var lastConnectedDevice: BluetoothDeviceItem? = null
    private var autoDeleteJob: Job? = null

    init {
        scope.launch {
            bluetoothController.devices.collect { devices ->
                _uiState.update { it.copy(devices = devices) }
            }
        }
        scope.launch {
            bluetoothController.events.collect { event ->
                handleBluetoothEvent(event)
            }
        }
        scope.launch {
            sessionKeyManager.hasSessionKey.collect { hasKey ->
                _uiState.update { state ->
                    val connected = state.connectionStatus as? ConnectionStatus.Connected
                    state.copy(
                        encryptionReady = hasKey,
                        connectionStatus = connected?.copy(encrypted = hasKey) ?: state.connectionStatus,
                    )
                }
            }
        }
    }

    fun startDiscovery() {
        _uiState.update {
            it.copy(
                connectionStatus = ConnectionStatus.Scanning,
                errorMessage = null,
            )
        }
        bluetoothController.startServer()
        bluetoothController.startDiscovery()
    }

    fun stopDiscovery() {
        bluetoothController.stopDiscovery()
        _uiState.update { state ->
            if (state.connectionStatus is ConnectionStatus.Scanning) {
                state.copy(connectionStatus = ConnectionStatus.Idle)
            } else {
                state
            }
        }
    }

    suspend fun connect(device: BluetoothDeviceItem) {
        lastConnectedDevice = device
        _uiState.update {
            it.copy(
                connectionStatus = ConnectionStatus.Connecting(device.name),
                activeDeviceName = device.name,
                errorMessage = null,
            )
        }
        bluetoothController.connect(device.address)
    }

    suspend fun reconnect() {
        val device = lastConnectedDevice ?: run {
            _uiState.update { it.copy(errorMessage = "No previous device to reconnect") }
            return
        }
        connect(device)
    }

    suspend fun sendText(message: String) {
        val trimmed = message.trim()
        if (trimmed.isEmpty()) return
        if (!_uiState.value.encryptionReady) {
            _uiState.update { it.copy(errorMessage = "Secure session is not ready yet") }
            return
        }

        val plaintext = trimmed.toByteArray(Charsets.UTF_8)
        val encryptedBytes = sessionKeyManager.useSessionKey { key ->
            encryptionManager.encrypt(plaintext, key).toWireBytes()
        }
        secureMemoryCleaner.clean(plaintext)

        if (encryptedBytes == null) {
            _uiState.update { it.copy(errorMessage = "Session key is unavailable") }
            return
        }

        bluetoothController.sendFrame(BluetoothFrame.EncryptedMessage(encryptedBytes))
        secureMemoryCleaner.clean(encryptedBytes)
        addMessage(trimmed, MessageDirection.Sent)
    }

    suspend fun disconnect() {
        bluetoothController.disconnect("Session ended")
        destroySession("Session ended")
    }

    suspend fun panic() {
        bluetoothController.disconnect("Panic mode activated")
        destroySession("Panic mode activated")
        _uiState.update {
            it.copy(
                connectionStatus = ConnectionStatus.Idle,
                errorMessage = null,
            )
        }
    }

    fun setBiometricLockEnabled(enabled: Boolean) {
        _uiState.update {
            it.copy(settings = it.settings.copy(biometricLockEnabled = enabled))
        }
    }

    fun setPanicModeEnabled(enabled: Boolean) {
        _uiState.update {
            it.copy(settings = it.settings.copy(panicModeEnabled = enabled))
        }
    }

    fun setAutoDeleteMinutes(minutes: Int) {
        val safeMinutes = minutes.coerceIn(MIN_AUTO_DELETE_MINUTES, MAX_AUTO_DELETE_MINUTES)
        _uiState.update {
            it.copy(settings = it.settings.copy(autoDeleteMinutes = safeMinutes))
        }
        if (_uiState.value.connectionStatus is ConnectionStatus.Connected) {
            scheduleAutoDelete()
        }
    }

    fun clearTransientError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun shutdown() {
        scope.launch {
            disconnect()
        }
        bluetoothController.release()
    }

    private suspend fun handleBluetoothEvent(event: BluetoothEvent) {
        when (event) {
            is BluetoothEvent.Connected -> {
                bluetoothController.stopDiscovery()
                destroySensitiveStateOnly()
                _uiState.update {
                    it.copy(
                        messages = emptyList(),
                        connectionStatus = ConnectionStatus.Connected(event.deviceName, encrypted = false),
                        activeDeviceName = event.deviceName,
                        encryptionReady = false,
                        errorMessage = null,
                    )
                }
                scheduleAutoDelete()
                beginKeyExchange()
            }

            is BluetoothEvent.FrameReceived -> handleFrame(event.frame)

            is BluetoothEvent.Disconnected -> destroySession(event.reason ?: "Bluetooth disconnected")

            is BluetoothEvent.Error -> {
                _uiState.update {
                    it.copy(
                        connectionStatus = ConnectionStatus.Error(event.message),
                        errorMessage = event.message,
                    )
                }
            }
        }
    }

    private suspend fun beginKeyExchange() {
        val material = keyExchangeManager.generateKeyExchangeMaterial()
        keyExchangeMaterial = material
        bluetoothController.sendFrame(BluetoothFrame.PublicKey(material.encodedPublicKey))
    }

    private suspend fun handleFrame(frame: BluetoothFrame) {
        when (frame) {
            is BluetoothFrame.PublicKey -> handlePeerPublicKey(frame.payload)
            is BluetoothFrame.EncryptedMessage -> handleEncryptedMessage(frame.payload)
            BluetoothFrame.SessionEnd -> destroySession("Remote device ended the session")
        }
    }

    private suspend fun handlePeerPublicKey(peerPublicKey: ByteArray) {
        val material = keyExchangeMaterial ?: run {
            beginKeyExchange()
            keyExchangeMaterial
        } ?: return

        val sessionKey = try {
            keyExchangeManager.deriveAes256Key(material.keyPair.private, peerPublicKey)
        } catch (exception: Exception) {
            _uiState.update { it.copy(errorMessage = "Key exchange failed: ${exception.message.orEmpty()}") }
            destroySession("Key exchange failed")
            return
        }

        sessionKeyManager.replaceSessionKey(sessionKey)
        clearKeyExchangeMaterial()
    }

    private suspend fun handleEncryptedMessage(payloadBytes: ByteArray) {
        if (!_uiState.value.encryptionReady) {
            _uiState.update { it.copy(errorMessage = "Rejected message before secure session was ready") }
            return
        }

        val plaintext = try {
            val payload = EncryptedPayload.fromWireBytes(payloadBytes)
            sessionKeyManager.useSessionKey { key ->
                encryptionManager.decrypt(payload, key)
            }
        } catch (_: AEADBadTagException) {
            _uiState.update { it.copy(errorMessage = "Rejected corrupted encrypted message") }
            return
        } catch (exception: Exception) {
            _uiState.update { it.copy(errorMessage = "Could not decrypt message: ${exception.message.orEmpty()}") }
            return
        } ?: return

        val messageText = plaintext.toString(Charsets.UTF_8)
        secureMemoryCleaner.clean(plaintext)
        addMessage(messageText, MessageDirection.Received)
    }

    private fun addMessage(text: String, direction: MessageDirection) {
        val message = ChatMessage(
            id = ++messageIdCounter,
            text = text,
            direction = direction,
            timestampMillis = System.currentTimeMillis(),
        )
        _uiState.update { it.copy(messages = it.messages + message, errorMessage = null) }
    }

    private fun scheduleAutoDelete() {
        autoDeleteJob?.cancel()
        val minutes = _uiState.value.settings.autoDeleteMinutes
        autoDeleteJob = scope.launch {
            delay(minutes * 60_000L)
            autoDeleteJob = null
            bluetoothController.disconnect("Session expired")
            destroySession("Session expired")
        }
    }

    private suspend fun destroySession(reason: String) {
        val currentJob = currentCoroutineContext()[Job]
        if (autoDeleteJob != currentJob) {
            autoDeleteJob?.cancel()
        }
        autoDeleteJob = null
        destroySensitiveStateOnly()
        _uiState.update {
            it.copy(
                messages = emptyList(),
                encryptionReady = false,
                activeDeviceName = null,
                connectionStatus = ConnectionStatus.Disconnected(reason),
            )
        }
    }

    private suspend fun destroySensitiveStateOnly() {
        clearKeyExchangeMaterial()
        sessionKeyManager.destroySessionKey()
        messageIdCounter = 0L
    }

    private fun clearKeyExchangeMaterial() {
        secureMemoryCleaner.clean(keyExchangeMaterial?.encodedPublicKey)
        keyExchangeMaterial = null
    }

    companion object {
        private const val MIN_AUTO_DELETE_MINUTES = 1
        private const val MAX_AUTO_DELETE_MINUTES = 60
    }
}
