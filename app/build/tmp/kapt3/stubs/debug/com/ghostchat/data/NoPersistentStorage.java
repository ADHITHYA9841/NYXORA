package com.ghostchat.data;

/**
 * GhostChat intentionally keeps this package free of database or cloud data sources.
 *
 * Runtime session data is held in memory by the repository and destroyed on
 * disconnect, panic, or timeout.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ghostchat/data/NoPersistentStorage;", "", "()V", "app_debug"})
public final class NoPersistentStorage {
    @org.jetbrains.annotations.NotNull()
    public static final com.ghostchat.data.NoPersistentStorage INSTANCE = null;
    
    private NoPersistentStorage() {
        super();
    }
}