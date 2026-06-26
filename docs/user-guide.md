# User Guide

## Start a Chat

1. Open GhostChat on both phones.
2. Enable Bluetooth on both devices.
3. On the discovery screen, grant Bluetooth permissions.
4. Press `Scan`.
5. Select a nearby device and press `Connect`.
6. Wait until the chat header shows `AES-256-GCM`.
7. Send messages from the chat input.

## End a Chat

Press the disconnect icon in the chat header. GhostChat closes the Bluetooth session, deletes the in-memory message list, destroys the session key, and returns to the discovery screen.

## Panic Mode

When panic mode is enabled, the delete icon appears beside the message input. Pressing it immediately disconnects Bluetooth, clears messages, destroys keys, and returns to the discovery screen.

## Security Settings

- Biometric lock: prompts for biometric or device credential before enabling.
- Auto-delete timer: expires active sessions after the selected number of minutes.
- Panic mode: controls whether the emergency delete button appears during chat.
- Screenshot protection: always active while GhostChat is open.
