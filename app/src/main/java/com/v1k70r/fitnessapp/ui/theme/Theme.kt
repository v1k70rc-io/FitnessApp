package com.v1k70r.fitnessapp.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Vinculamos los colores que creaste en Color.kt con los roles de Material Design
private val DarkColorScheme = darkColorScheme(
    primary = MaterialRed700,
    onPrimary = TextWhite,
    primaryContainer = MaterialRed900,

    background = DarkBackground,
    onBackground = TextWhite,

    surface = DarkSurface,
    onSurface = TextWhite,

    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextGray
)

@Composable
fun FitnessAppTheme(
    // Forzamos que siempre sea true para mantener tu diseño Dark Mode
    darkTheme: Boolean = true,
    // Desactivamos el color dinámico para que no cambie tu rojo por los colores del sistema del usuario
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    // Configuración para la barra de estado superior (Hora, Batería, etc.)
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Pintamos la barra del mismo color oscuro que el fondo de la app
            window.statusBarColor = colorScheme.background.toArgb()
            // Hacemos que los íconos de la batería y hora sean blancos
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Utiliza las tipografías por defecto de Android Studio
        content = content
    )
}