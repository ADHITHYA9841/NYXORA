package com.ghostchat.bluetooth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 \u00062\u00020\u0001:\u0004\u0006\u0007\b\tR\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0003\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lcom/ghostchat/bluetooth/BluetoothFrame;", "", "payload", "", "getPayload", "()[B", "Companion", "EncryptedMessage", "PublicKey", "SessionEnd", "Lcom/ghostchat/bluetooth/BluetoothFrame$EncryptedMessage;", "Lcom/ghostchat/bluetooth/BluetoothFrame$PublicKey;", "Lcom/ghostchat/bluetooth/BluetoothFrame$SessionEnd;", "app_debug"})
public abstract interface BluetoothFrame {
    public static final int TYPE_PUBLIC_KEY = 1;
    public static final int TYPE_ENCRYPTED_MESSAGE = 2;
    public static final int TYPE_SESSION_END = 3;
    public static final int MAX_FRAME_BYTES = 65536;
    @org.jetbrains.annotations.NotNull()
    public static final com.ghostchat.bluetooth.BluetoothFrame.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract byte[] getPayload();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/ghostchat/bluetooth/BluetoothFrame$Companion;", "", "()V", "MAX_FRAME_BYTES", "", "TYPE_ENCRYPTED_MESSAGE", "TYPE_PUBLIC_KEY", "TYPE_SESSION_END", "from", "Lcom/ghostchat/bluetooth/BluetoothFrame;", "type", "payload", "", "typeOf", "frame", "app_debug"})
    public static final class Companion {
        public static final int TYPE_PUBLIC_KEY = 1;
        public static final int TYPE_ENCRYPTED_MESSAGE = 2;
        public static final int TYPE_SESSION_END = 3;
        public static final int MAX_FRAME_BYTES = 65536;
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.ghostchat.bluetooth.BluetoothFrame from(int type, @org.jetbrains.annotations.NotNull()
        byte[] payload) {
            return null;
        }
        
        public final int typeOf(@org.jetbrains.annotations.NotNull()
        com.ghostchat.bluetooth.BluetoothFrame frame) {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/ghostchat/bluetooth/BluetoothFrame$EncryptedMessage;", "Lcom/ghostchat/bluetooth/BluetoothFrame;", "payload", "", "([B)V", "getPayload", "()[B", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class EncryptedMessage implements com.ghostchat.bluetooth.BluetoothFrame {
        @org.jetbrains.annotations.NotNull()
        private final byte[] payload = null;
        
        public EncryptedMessage(@org.jetbrains.annotations.NotNull()
        byte[] payload) {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public byte[] getPayload() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final byte[] component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.ghostchat.bluetooth.BluetoothFrame.EncryptedMessage copy(@org.jetbrains.annotations.NotNull()
        byte[] payload) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/ghostchat/bluetooth/BluetoothFrame$PublicKey;", "Lcom/ghostchat/bluetooth/BluetoothFrame;", "payload", "", "([B)V", "getPayload", "()[B", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class PublicKey implements com.ghostchat.bluetooth.BluetoothFrame {
        @org.jetbrains.annotations.NotNull()
        private final byte[] payload = null;
        
        public PublicKey(@org.jetbrains.annotations.NotNull()
        byte[] payload) {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public byte[] getPayload() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final byte[] component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.ghostchat.bluetooth.BluetoothFrame.PublicKey copy(@org.jetbrains.annotations.NotNull()
        byte[] payload) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/ghostchat/bluetooth/BluetoothFrame$SessionEnd;", "Lcom/ghostchat/bluetooth/BluetoothFrame;", "()V", "payload", "", "getPayload", "()[B", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class SessionEnd implements com.ghostchat.bluetooth.BluetoothFrame {
        @org.jetbrains.annotations.NotNull()
        private static final byte[] payload = null;
        @org.jetbrains.annotations.NotNull()
        public static final com.ghostchat.bluetooth.BluetoothFrame.SessionEnd INSTANCE = null;
        
        private SessionEnd() {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public byte[] getPayload() {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}