# Connection Sequence

```mermaid
sequenceDiagram
    participant A as Device A
    participant B as Device B
    participant AR as A Repository
    participant BR as B Repository

    A->>B: Bluetooth Classic RFCOMM connect
    B-->>A: RFCOMM accepted
    AR->>AR: Generate ECDH key pair
    BR->>BR: Generate ECDH key pair
    AR->>BR: PublicKey frame
    BR->>AR: PublicKey frame
    AR->>AR: Derive AES-256 key with ECDH + HKDF
    BR->>BR: Derive AES-256 key with ECDH + HKDF
    AR->>BR: AES-GCM encrypted message frame
    BR->>BR: Verify tag and decrypt
    BR->>AR: AES-GCM encrypted message frame
    AR->>AR: Verify tag and decrypt
    A->>B: SessionEnd frame or socket close
    AR->>AR: Clear messages and destroy keys
    BR->>BR: Clear messages and destroy keys
```

## Frame Types

| Type | Payload |
| --- | --- |
| `1` | X.509 encoded ECDH public key |
| `2` | AES-GCM encrypted payload: nonce length, nonce, ciphertext+tag length, ciphertext+tag |
| `3` | Empty session-end signal |
