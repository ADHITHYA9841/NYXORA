package com.ghostchat.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = GhostGreen,
    onPrimary = GhostSurface,
    secondary = GhostBlue,
    tertiary = GhostAmber,
    error = GhostCoral,
    background = GhostSurface,
    onBackground = GhostInk,
    surface = GhostPanel,
    onSurface = GhostInk,
)

private val DarkColorScheme = darkColorScheme(
    primary = GhostDarkGreen,
    onPrimary = GhostInk,
    secondary = GhostMint,
    tertiary = GhostDarkAmber,
    error = GhostDarkCoral,
    background = GhostDarkSurface,
    onBackground = GhostDarkInk,
    surface = GhostDarkPanel,
    onSurface = GhostDarkInk,
)

@Composable
fun GhostChatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = androidx.compose.material3.Typography(),
        content = content,
    )
}
