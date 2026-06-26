package com.ghostchat.crypto

import java.util.Arrays
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecureMemoryCleaner @Inject constructor() {
    fun clean(bytes: ByteArray?) {
        if (bytes != null) {
            Arrays.fill(bytes, 0)
        }
    }

    fun clean(chars: CharArray?) {
        if (chars != null) {
            Arrays.fill(chars, '\u0000')
        }
    }
}
