# Security Design

## Goals

- Prevent plaintext message transmission.
- Use a fresh encryption key for every Bluetooth session.
- Authenticate encrypted messages and reject modified payloads.
- Avoid recoverable message history after session termination.
- Avoid cloud, server, analytics, and internet communication.

## Cryptography

GhostChat performs ECDH on the `secp256r1` curve when a Bluetooth socket connects. The shared ECDH secret is passed through HKDF-SHA256 with GhostChat-specific salt and info strings to derive a 32-byte AES key.

Messages use `AES/GCM/NoPadding` with:

- 256-bit AES key.
- 96-bit random nonce per message.
- 128-bit authentication tag.
- Fixed associated data: `GhostChat-v1-message`.

AES-GCM tag verification is mandatory. If decryption fails or the tag is invalid, the message is rejected and never added to the chat state.

## Session Destruction

The repository destroys session state when:

- Bluetooth disconnects.
- The user presses disconnect.
- The auto-delete timer expires.
- Panic mode is activated.
- A session-end frame is received.

Destruction clears:

- In-memory message list.
- Pending ECDH public key buffers.
- AES session key bytes.
- Conversation status and active device state.

## Privacy Controls

- `android.permission.INTERNET` is not declared.
- Android backup and device transfer rules exclude app data.
- `WindowManager.LayoutParams.FLAG_SECURE` blocks screenshots and screen recording while the app is open.
- Biometric lock uses AndroidX Biometric and device credentials where available.

## Limitations

Secure deletion on Android/Kotlin is best effort. Immutable `String` objects, framework socket buffers, and garbage-collected copies cannot be reliably overwritten. GhostChat reduces exposure by avoiding persistence and wiping mutable byte arrays that contain keys or plaintext.
