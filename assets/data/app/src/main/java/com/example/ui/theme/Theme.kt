package com.example.ui.theme

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF56D6C6),
    onPrimary = Color(0xFF003732),
    primaryContainer = Color(0xFF005048),
    onPrimaryContainer = Color(0xFF75F3E2),
    secondary = Color(0xFFAAC7FF),
    onSecondary = Color(0xFF0A305F),
    secondaryContainer = Color(0xFF284777),
    onSecondaryContainer = Color(0xFFD6E3FF),
    tertiary = Color(0xFFFFB77C),
    onTertiary = Color(0xFF4C2700),
    tertiaryContainer = Color(0xFF6C3A00),
    onTertiaryContainer = Color(0xFFFFDCC2),
    background = Color(0xFF191C1B),
    onBackground = Color(0xFFE1E3E1),
    surface = Color(0xFF191C1B),
    onSurface = Color(0xFFE1E3E1),
    surfaceVariant = Color(0xFF3F4947),
    onSurfaceVariant = Color(0xFFBFC9C7)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF006A60),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFCCE8E3),
    onPrimaryContainer = Color(0xFF00201C),
    secondary = Color(0xFF415F91),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDDE4F8),
    onSecondaryContainer = Color(0xFF001D4B),
    tertiary = Color(0xFF8F4E00),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFADCC0),
    onTertiaryContainer = Color(0xFF2E1500),
    background = Color(0xFFF7F9F8),
    onBackground = Color(0xFF191C1B),
    surface = Color(0xFFF7F9F8),
    onSurface = Color(0xFF191C1B),
    surfaceVariant = Color(0xFFE0E3E1),
    onSurfaceVariant = Color(0xFF3F4947)
)

@Composable
fun PhysioPilotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep it false to enforce our medical app identity
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
