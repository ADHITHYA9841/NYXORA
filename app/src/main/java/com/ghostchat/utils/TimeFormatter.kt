package com.ghostchat.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeFormatter {
    private val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    fun shortTime(timestampMillis: Long): String = formatter.format(Date(timestampMillis))
}
