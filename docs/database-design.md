# Database Design

GhostChat intentionally has no database.

## Rationale

The security rule is that GhostChat must leave no recoverable conversation history after session termination. To satisfy that requirement, messages are held only in a `StateFlow`-backed in-memory list during an active Bluetooth session. The app does not use Room, SQLite, DataStore, SharedPreferences, Firebase, cloud storage, or analytics.

## Temporary State

| State | Location | Lifetime |
| --- | --- | --- |
| Discovered Bluetooth devices | In-memory `StateFlow` | Until process exit or next scan update |
| Chat messages | In-memory `StateFlow` | Active session only |
| ECDH key pair | Repository field | Key exchange only |
| AES session key bytes | `SessionKeyManager` memory | Active session only |
| Security toggles | In-memory `StateFlow` | App process only |

On disconnect, panic, or timeout, the repository clears the message list and asks `SessionKeyManager` to wipe and drop the session key.
