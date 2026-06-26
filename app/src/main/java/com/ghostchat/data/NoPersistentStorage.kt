package com.ghostchat.data

/**
 * GhostChat intentionally keeps this package free of database or cloud data sources.
 *
 * Runtime session data is held in memory by the repository and destroyed on
 * disconnect, panic, or timeout.
 */
object NoPersistentStorage
