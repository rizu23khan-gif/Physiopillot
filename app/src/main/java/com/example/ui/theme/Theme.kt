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
    primary = MedicalBluePrimaryDark,
    onPrimary = MedicalBlueOnPrimaryDark,
    primaryContainer = MedicalBluePrimaryContainerDark,
    onPrimaryContainer = MedicalBlueOnPrimaryContainerDark,
    secondary = MedicalBlueSecondaryDark,
    onSecondary = MedicalBlueOnSecondaryDark,
    secondaryContainer = MedicalBlueSecondaryContainerDark,
    onSecondaryContainer = MedicalBlueOnSecondaryContainerDark,
    tertiary = MedicalBluePrimaryDark,
    onTertiary = MedicalBlueOnPrimaryDark,
    tertiaryContainer = MedicalBluePrimaryContainerDark,
    onTertiaryContainer = MedicalBlueOnPrimaryContainerDark,
    background = MedicalBackgroundDark,
    onBackground = MedicalOnBackgroundDark,
    surface = MedicalSurfaceDark,
    onSurface = MedicalOnSurfaceDark,
    surfaceVariant = MedicalSurfaceVariantDark,
    onSurfaceVariant = MedicalOnSurfaceVariantDark,
    outline = MedicalOutlineDark
)

private val LightColorScheme = lightColorScheme(
    primary = MedicalBluePrimary,
    onPrimary = MedicalBlueOnPrimary,
    primaryContainer = MedicalBluePrimaryContainer,
    onPrimaryContainer = MedicalBlueOnPrimaryContainer,
    secondary = MedicalBlueSecondary,
    onSecondary = MedicalBlueOnSecondary,
    secondaryContainer = MedicalBlueSecondaryContainer,
    onSecondaryContainer = MedicalBlueOnSecondaryContainer,
    tertiary = MedicalBlueTertiary,
    onTertiary = MedicalBlueOnTertiary,
    tertiaryContainer = MedicalBlueTertiaryContainer,
    onTertiaryContainer = MedicalBlueOnTertiaryContainer,
    background = MedicalBackground,
    onBackground = MedicalOnBackground,
    surface = MedicalSurface,
    onSurface = MedicalOnSurface,
    surfaceVariant = MedicalSurfaceVariant,
    onSurfaceVariant = MedicalOnSurfaceVariant,
    outline = MedicalOutline
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
