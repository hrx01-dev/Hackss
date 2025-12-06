package com.runanywhere.startup_hackathon20.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Light Green Theme
private val LightGreenColorScheme = lightColorScheme(
    primary = Green80,
    onPrimary = White,
    primaryContainer = GreenLight,
    onPrimaryContainer = DarkGray,

    secondary = GreenDark,
    onSecondary = White,
    secondaryContainer = GreenLight,
    onSecondaryContainer = DarkGray,

    tertiary = GreenDark,
    onTertiary = White,

    background = GreenBackground,
    onBackground = Black,

    surface = GreenSurface,
    onSurface = Black,

    surfaceVariant = LightGray,
    onSurfaceVariant = DarkGray,

    error = androidx.compose.ui.graphics.Color(0xFFEF4444),
    onError = White
)

// Dark Theme (Indigo/Slate)
private val DarkColorScheme = darkColorScheme(
    primary = DarkSecondary,
    onPrimary = White,
    primaryContainer = DarkPrimary,
    onPrimaryContainer = White,

    secondary = DarkTertiary,
    onSecondary = Black,
    secondaryContainer = DarkPrimary,
    onSecondaryContainer = White,

    tertiary = DarkTertiary,
    onTertiary = Black,

    background = DarkBackground,
    onBackground = White,

    surface = DarkSurface,
    onSurface = White,

    surfaceVariant = androidx.compose.ui.graphics.Color(0xFF334155),
    onSurfaceVariant = LightGray,

    error = androidx.compose.ui.graphics.Color(0xFFF87171),
    onError = Black
)

@Composable
fun Startup_hackathon20Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disabled to use our custom theme
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightGreenColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}