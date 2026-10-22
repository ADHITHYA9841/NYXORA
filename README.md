# NYXORA

NYXORA is a privacy-focused Android messaging app for direct Bluetooth Classic chats between two nearby Android devices. It uses ephemeral ECDH session keys and AES-256-GCM encrypted message frames. Messages and key material live only in memory and are cleared when the session ends.

## Features

- Bluetooth Classic discovery, paired-device listing, server accept, and client connection.
- ECDH key exchange on every connection.
- AES-256-GCM message encryption with authentication-tag verification.
- No internet permission, no Firebase, no cloud storage, no analytics.
- In-memory messages only.
- Panic mode to disconnect, clear messages, destroy keys, and return home.
- Auto-delete timer for active sessions.
- Screenshot and screen-recording protection via `FLAG_SECURE` in release builds; disabled in debug builds for development and research screenshots.
- Material 3 Compose UI with dark theme support.
- Biometric setting flow using AndroidX Biometric.

## Project Structure

```text
com.ghostchat
|-- bluetooth      Bluetooth Classic transport and frame protocol
|-- crypto         ECDH, HKDF, AES-GCM, session keys, memory cleaning
|-- data           Reserved for future data sources; no persistence is used
|-- di             Hilt modules
|-- domain/model   UI and domain state models
|-- repository     Session orchestration and MVVM data boundary
|-- security       Screenshot protection and biometric prompt helpers
|-- ui             Compose screens, components, theme, ViewModel
`-- utils          Small formatting helpers
```

## Build

Open the project in Android Studio, let Gradle sync, then run the `app` configuration on a physical Android device. Bluetooth Classic requires real hardware; an emulator is not enough for end-to-end testing.

JVM crypto tests:

```powershell
./gradlew testDebugUnitTest
```

### Production release signing

Production APKs use a private release keystore that must never be committed. Create `keystore.properties` in the project root with `storeFile=release/nyxora-release.jks`, `storePassword`, `keyAlias`, and `keyPassword`. Keep both that file and the keystore backed up securely; every future update must use the same key. The release build fails when signing is not configured. Build with `./gradlew assembleRelease`; the APK is written to `app/build/outputs/apk/release/`.

## Documentation

- [System architecture](docs/architecture.md)
- [Connection sequence](docs/sequence-diagram.md)
- [Database design](docs/database-design.md)
- [Security design](docs/security-design.md)
- [Setup instructions](docs/setup.md)
- [User guide](docs/user-guide.md)

## Security Notes

NYXORA uses best-effort memory clearing. Kotlin/JVM `String`, Android framework internals, Bluetooth stack buffers, and garbage-collected object movement cannot be securely overwritten with the same guarantees as native memory. The app avoids persistence, excludes backups/transfers, does not request internet permission, clears in-memory state on session termination, and wipes mutable byte buffers wherever practical.
