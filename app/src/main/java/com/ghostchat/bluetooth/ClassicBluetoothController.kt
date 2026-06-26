package com.ghostchat.bluetooth

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.ghostchat.domain.model.BluetoothDeviceItem
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.EOFException
import java.io.IOException
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

@Singleton
class ClassicBluetoothController @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val bluetoothManager = context.getSystemService(BluetoothManager::class.java)
    private val bluetoothAdapter: BluetoothAdapter? = bluetoothManager?.adapter
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val writeMutex = Mutex()

    private var discoveryReceiverRegistered = false
    private var acceptSocket: BluetoothServerSocket? = null
    private var activeSocket: BluetoothSocket? = null
    private var outputStream: DataOutputStream? = null

    private val _devices = MutableStateFlow<List<BluetoothDeviceItem>>(emptyList())
    val devices: StateFlow<List<BluetoothDeviceItem>> = _devices

    private val _events = MutableSharedFlow<BluetoothEvent>(extraBufferCapacity = 64)
    val events: SharedFlow<BluetoothEvent> = _events

    private val discoveryReceiver = object : BroadcastReceiver() {
        override fun onReceive(receiverContext: Context, intent: Intent) {
            when (intent.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device = intent.bluetoothDeviceExtra() ?: return
                    addDevice(device)
                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> Unit
            }
        }
    }

    fun startDiscovery() {
        val adapter = requireBluetoothAdapter() ?: return
        if (!hasDiscoveryPermission()) {
            emitError("Bluetooth discovery permission has not been granted")
            return
        }

        registerDiscoveryReceiver()
        _devices.value = pairedDevices()

        try {
            if (adapter.isDiscovering) {
                adapter.cancelDiscovery()
            }
            adapter.startDiscovery()
        } catch (securityException: SecurityException) {
            emitError("Bluetooth discovery permission denied")
        }
    }

    fun stopDiscovery() {
        val adapter = bluetoothAdapter ?: return
        if (!hasDiscoveryPermission()) return
        try {
            if (adapter.isDiscovering) {
                adapter.cancelDiscovery()
            }
        } catch (_: SecurityException) {
            // Permission may have been revoked while discovery was active.
        }
    }

    fun startServer() {
        val adapter = requireBluetoothAdapter() ?: return
        if (!hasConnectPermission()) {
            emitError("Bluetooth connection permission has not been granted")
            return
        }

        scope.launch {
            closeAcceptSocket()
            try {
                acceptSocket = adapter.listenUsingRfcommWithServiceRecord(APP_NAME, SERVICE_UUID)
                val socket = acceptSocket?.accept()
                if (socket != null) {
                    closeAcceptSocket()
                    handleConnectedSocket(socket)
                }
            } catch (securityException: SecurityException) {
                _events.emit(BluetoothEvent.Error("Bluetooth server permission denied"))
            } catch (ioException: IOException) {
                if (activeSocket == null) {
                    _events.emit(BluetoothEvent.Disconnected(ioException.message))
                }
            }
        }
    }

    suspend fun connect(address: String) {
        val adapter = requireBluetoothAdapter() ?: return
        if (!hasConnectPermission()) {
            _events.emit(BluetoothEvent.Error("Bluetooth connection permission has not been granted"))
            return
        }

        withContext(Dispatchers.IO) {
            stopDiscovery()
            closeActiveSocket()
            closeAcceptSocket()

            try {
                val device = adapter.getRemoteDevice(address)
                val socket = device.createRfcommSocketToServiceRecord(SERVICE_UUID)
                socket.connect()
                handleConnectedSocket(socket)
            } catch (securityException: SecurityException) {
                _events.emit(BluetoothEvent.Error("Bluetooth connection permission denied"))
            } catch (ioException: IOException) {
                closeActiveSocket()
                _events.emit(BluetoothEvent.Error("Failed to connect: ${ioException.message.orEmpty()}"))
            }
        }
    }

    suspend fun sendFrame(frame: BluetoothFrame) {
        val stream = outputStream ?: run {
            _events.emit(BluetoothEvent.Error("No active Bluetooth connection"))
            return
        }

        withContext(Dispatchers.IO) {
            writeMutex.withLock {
                try {
                    stream.writeByte(BluetoothFrame.typeOf(frame))
                    stream.writeInt(frame.payload.size)
                    stream.write(frame.payload)
                    stream.flush()
                } catch (ioException: IOException) {
                    _events.emit(BluetoothEvent.Disconnected(ioException.message))
                    closeActiveSocket()
                }
            }
        }
    }

    suspend fun disconnect(reason: String? = "Disconnected") {
        sendSessionEndIfPossible()
        closeActiveSocket()
        closeAcceptSocket()
        _events.emit(BluetoothEvent.Disconnected(reason))
    }

    fun release() {
        unregisterDiscoveryReceiver()
        stopDiscovery()
        scope.cancel()
        closeActiveSocket()
        closeAcceptSocket()
    }

    private suspend fun sendSessionEndIfPossible() {
        if (outputStream == null) return
        runCatching { sendFrame(BluetoothFrame.SessionEnd) }
    }

    @SuppressLint("MissingPermission")
    private suspend fun handleConnectedSocket(socket: BluetoothSocket) {
        closeActiveSocket()
        activeSocket = socket
        outputStream = DataOutputStream(socket.outputStream)
        val inputStream = DataInputStream(socket.inputStream)
        val remoteDevice = socket.remoteDevice
        val deviceName = remoteDevice.safeName()
        val address = remoteDevice.address.orEmpty()
        _events.emit(BluetoothEvent.Connected(deviceName, address))

        scope.launch {
            readLoop(inputStream)
        }
    }

    private suspend fun readLoop(inputStream: DataInputStream) {
        try {
            while (true) {
                val type = inputStream.readUnsignedByte()
                val size = inputStream.readInt()
                if (size < 0 || size > BluetoothFrame.MAX_FRAME_BYTES) {
                    _events.emit(BluetoothEvent.Error("Rejected malformed Bluetooth frame"))
                    closeActiveSocket()
                    return
                }

                val payload = ByteArray(size)
                inputStream.readFully(payload)
                _events.emit(BluetoothEvent.FrameReceived(BluetoothFrame.from(type, payload)))
            }
        } catch (_: EOFException) {
            _events.emit(BluetoothEvent.Disconnected("Bluetooth connection closed"))
        } catch (ioException: IOException) {
            _events.emit(BluetoothEvent.Disconnected(ioException.message))
        } catch (illegalArgumentException: IllegalArgumentException) {
            _events.emit(BluetoothEvent.Error(illegalArgumentException.message.orEmpty()))
        } finally {
            closeActiveSocket()
        }
    }

    private fun registerDiscoveryReceiver() {
        if (discoveryReceiverRegistered) return
        val filter = IntentFilter().apply {
            addAction(BluetoothDevice.ACTION_FOUND)
            addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(discoveryReceiver, filter, Context.RECEIVER_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            context.registerReceiver(discoveryReceiver, filter)
        }
        discoveryReceiverRegistered = true
    }

    private fun unregisterDiscoveryReceiver() {
        if (!discoveryReceiverRegistered) return
        runCatching { context.unregisterReceiver(discoveryReceiver) }
        discoveryReceiverRegistered = false
    }

    private fun addDevice(device: BluetoothDevice) {
        val item = device.toItem() ?: return
        val updated = _devices.value
            .filterNot { it.address == item.address }
            .plus(item)
            .sortedWith(compareByDescending<BluetoothDeviceItem> { it.bonded }.thenBy { it.name })
        _devices.value = updated
    }

    @SuppressLint("MissingPermission")
    private fun pairedDevices(): List<BluetoothDeviceItem> {
        val adapter = bluetoothAdapter ?: return emptyList()
        if (!hasConnectPermission()) return emptyList()
        return try {
            adapter.bondedDevices.mapNotNull { it.toItem() }
        } catch (_: SecurityException) {
            emptyList()
        }
    }

    @SuppressLint("MissingPermission")
    private fun BluetoothDevice.toItem(): BluetoothDeviceItem? {
        val address = address ?: return null
        val fallbackName = "Unknown device"
        return try {
            BluetoothDeviceItem(
                address = address,
                name = name ?: fallbackName,
                bonded = bondState == BluetoothDevice.BOND_BONDED,
            )
        } catch (_: SecurityException) {
            BluetoothDeviceItem(
                address = address,
                name = fallbackName,
                bonded = false,
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun BluetoothDevice.safeName(): String =
        try {
            name ?: "Unknown device"
        } catch (_: SecurityException) {
            "Unknown device"
        }

    private fun requireBluetoothAdapter(): BluetoothAdapter? {
        val adapter = bluetoothAdapter
        if (adapter == null) {
            emitError("Bluetooth is not available on this device")
        } else if (!adapter.isEnabled) {
            emitError("Bluetooth is disabled")
        }
        return adapter?.takeIf { it.isEnabled }
    }

    private fun emitError(message: String) {
        _events.tryEmit(BluetoothEvent.Error(message))
    }

    private fun hasDiscoveryPermission(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            hasPermission(Manifest.permission.BLUETOOTH_SCAN)
        } else {
            hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        }

    private fun hasConnectPermission(): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.S ||
            hasPermission(Manifest.permission.BLUETOOTH_CONNECT)

    private fun hasPermission(permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    private fun closeActiveSocket() {
        runCatching { outputStream?.close() }
        outputStream = null
        runCatching { activeSocket?.close() }
        activeSocket = null
    }

    private fun closeAcceptSocket() {
        runCatching { acceptSocket?.close() }
        acceptSocket = null
    }

    private fun Intent.bluetoothDeviceExtra(): BluetoothDevice? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)
        } else {
            @Suppress("DEPRECATION")
            getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
        }

    companion object {
        private const val APP_NAME = "GhostChat"
        val SERVICE_UUID: UUID = UUID.fromString("d91f4ad4-6c6d-4f5f-9a44-2ff2b1e5f6c2")
    }
}
