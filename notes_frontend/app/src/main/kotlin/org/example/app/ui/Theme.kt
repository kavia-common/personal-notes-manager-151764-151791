package org.example.app.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// PUBLIC_INTERFACE
/**
 * Light color scheme with custom project colors.
 */
private val LightColors = lightColorScheme(
    primary = Color(0xFF1976d2),
    onPrimary = Color.White,
    secondary = Color(0xFF424242),
    onSecondary = Color.White,
    tertiary = Color(0xFFffca28),
    background = Color.White,
    surface = Color.White,
    onSurface = Color(0xFF212121),
    error = Color(0xFFE53935)
)

// PUBLIC_INTERFACE
@Composable
fun NotesAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(),
        content = content
    )
}
