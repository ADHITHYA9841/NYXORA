# Setup Instructions

## Requirements

- Android Studio with Android SDK platform 35 installed.
- JDK 17 configured for Gradle.
- Two physical Android devices with Bluetooth Classic support.
- USB debugging or another deployment method for both devices.

## Build and Run

1. Open this folder in Android Studio.
2. Wait for Gradle sync to complete.
3. Connect a physical Android device.
4. Select the `app` run configuration.
5. Build and run.
6. Repeat on a second physical Android device.

## Device Preparation

1. Enable Bluetooth on both devices.
2. Make at least one device discoverable from Android system Bluetooth settings.
3. Launch GhostChat on both devices.
4. Grant Bluetooth permissions when prompted.
5. Press `Scan`, select a peer, and connect.

## Tests

Run JVM crypto tests from the project root:

```powershell
./gradlew testDebugUnitTest
```

Bluetooth behavior must be tested on physical devices because emulators do not provide reliable Bluetooth Classic peer-to-peer sockets.
