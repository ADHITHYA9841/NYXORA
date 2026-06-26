package com.ghostchat.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghostchat.domain.model.BluetoothDeviceItem
import com.ghostchat.repository.GhostChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class GhostChatViewModel @Inject constructor(
    private val repository: GhostChatRepository,
) : ViewModel() {
    val uiState = repository.uiState

    fun startDiscovery() {
        repository.startDiscovery()
    }

    fun stopDiscovery() {
        repository.stopDiscovery()
    }

    fun connect(device: BluetoothDeviceItem) {
        viewModelScope.launch {
            repository.connect(device)
        }
    }

    fun reconnect() {
        viewModelScope.launch {
            repository.reconnect()
        }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            repository.sendText(message)
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            repository.disconnect()
        }
    }

    fun panic() {
        viewModelScope.launch {
            repository.panic()
        }
    }

    fun setBiometricLockEnabled(enabled: Boolean) {
        repository.setBiometricLockEnabled(enabled)
    }

    fun setPanicModeEnabled(enabled: Boolean) {
        repository.setPanicModeEnabled(enabled)
    }

    fun setAutoDeleteMinutes(minutes: Int) {
        repository.setAutoDeleteMinutes(minutes)
    }

    fun clearTransientError() {
        repository.clearTransientError()
    }

    override fun onCleared() {
        repository.stopDiscovery()
        super.onCleared()
    }
}
