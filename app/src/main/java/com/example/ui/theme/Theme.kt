package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = TerracottaPrimaryDark,
    onPrimary = TerracottaOnPrimaryContainer,
    primaryContainer = TerracottaPrimary,
    onPrimaryContainer = Color.White,
    secondary = FestiveGoldLight,
    onSecondary = Color.Black,
    secondaryContainer = FestiveGold,
    onSecondaryContainer = FestiveGoldContainer,
    tertiary = MoonGold,
    background = BackgroundDark,
    surface = SurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onBackground = Color(0xFFF3EBE8),
    onSurface = Color(0xFFF3EBE8)
)

private val LightColorScheme = lightColorScheme(
    primary = TerracottaPrimary,
    onPrimary = TerracottaOnPrimary,
    primaryContainer = TerracottaPrimaryContainer,
    onPrimaryContainer = TerracottaOnPrimaryContainer,
    secondary = FestiveGold,
    onSecondary = Color.White,
    secondaryContainer = FestiveGoldContainer,
    onSecondaryContainer = FestiveGoldOnContainer,
    tertiary = MoonGold,
    background = BackgroundLight,
    surface = SurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onBackground = NeutralDark,
    onSurface = NeutralDark
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Preserve warm terracotta & gold Bengali aesthetics
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
        typography = Typography,
        content = content
    )
}
