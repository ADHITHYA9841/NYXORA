package com.ghostchat.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun GhostLogo(modifier: Modifier = Modifier) {
    val primary = MaterialTheme.colorScheme.primary
    val onSurface = MaterialTheme.colorScheme.onSurface
    val surface = MaterialTheme.colorScheme.surface

    Canvas(modifier = modifier.size(96.dp)) {
        drawRoundRect(
            color = primary,
            topLeft = Offset(size.width * 0.18f, size.height * 0.14f),
            size = Size(size.width * 0.64f, size.height * 0.62f),
            cornerRadius = CornerRadius(size.width * 0.22f, size.width * 0.22f),
        )

        val base = Path().apply {
            moveTo(size.width * 0.18f, size.height * 0.62f)
            lineTo(size.width * 0.28f, size.height * 0.82f)
            lineTo(size.width * 0.39f, size.height * 0.68f)
            lineTo(size.width * 0.50f, size.height * 0.82f)
            lineTo(size.width * 0.61f, size.height * 0.68f)
            lineTo(size.width * 0.72f, size.height * 0.82f)
            lineTo(size.width * 0.82f, size.height * 0.62f)
            close()
        }
        drawPath(base, primary)

        drawCircle(surface, radius = size.width * 0.055f, center = Offset(size.width * 0.39f, size.height * 0.43f))
        drawCircle(surface, radius = size.width * 0.055f, center = Offset(size.width * 0.61f, size.height * 0.43f))
        drawRoundRect(
            color = surface,
            topLeft = Offset(size.width * 0.36f, size.height * 0.56f),
            size = Size(size.width * 0.28f, size.height * 0.16f),
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx()),
        )
        drawCircle(onSurface, radius = size.width * 0.028f, center = Offset(size.width * 0.50f, size.height * 0.64f))
        drawRoundRect(
            color = onSurface,
            topLeft = Offset(size.width * 0.44f, size.height * 0.50f),
            size = Size(size.width * 0.12f, size.height * 0.10f),
            cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx()),
            style = Stroke(width = 2.dp.toPx()),
        )
    }
}
