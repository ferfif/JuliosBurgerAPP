package com.juliosburger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.juliosburger.presentation.screen.CategoriesScreen
import com.juliosburger.ui.theme.JuliosBurgerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JuliosBurgerTheme {
                CategoriesScreen()
            }
        }
    }
}
