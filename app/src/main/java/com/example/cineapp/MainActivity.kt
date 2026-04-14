package com.example.cineapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // Forzamos el color azul marino en la base de la app
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF0A192F)
                ) {
                    AppNavigation()
                }
            }
        }
    }
}