package com.juliosburger.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

data class JuliosColors(
    val primary: Color = Color(0xFFD32F2F),
    val background: Color = Color(0xFFFFFBFE),
    val surface: Color = Color(0xFFFFFBFE)
)

val LocalJuliosColors = androidx.compose.runtime.staticCompositionLocalOf { JuliosColors() }

@Composable
fun JuliosBurgerTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalJuliosColors provides JuliosColors()) {
        content()
    }
}
