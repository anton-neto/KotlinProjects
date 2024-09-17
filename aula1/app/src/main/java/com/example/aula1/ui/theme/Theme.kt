package com.example.aula1.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Definindo as cores para o modo escuro
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),  // Purple
    secondary = Color(0xFF03DAC6), // Teal
    tertiary = Color(0xFFCF6679),  // Pinkish-red
    background = Color(0xFF121212),  // Fundo para o modo escuro
    surface = Color(0xFF1F1F1F),    // Cor da superfície
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White
)

// Definindo as cores para o modo claro
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),  // Purple
    secondary = Color(0xFF03DAC6), // Teal
    tertiary = Color(0xFFB00020),  // Pinkish-red
    background = Color(0xFFFFFBFE), // Fundo para o modo claro
    surface = Color(0xFFF0F0F0),    // Cor da superfície
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.White
)

@Composable
fun Aula1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}