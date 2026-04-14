package com.example.cineapp

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToHome: () -> Unit) {
    // Variables para controlar la animación de tamaño y opacidad
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }

    // Este bloque se ejecuta una sola vez al abrir la pantalla
    LaunchedEffect(key1 = true) {
        // 1. Aparece haciéndose grande (1.5 segundos)
        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
        )
        // 2. Efecto de rebote hasta su tamaño normal
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        // 3. La animación anterior toma unos 2.5 segundos en total.
        // Esperamos otros 2.5 segundos para cumplir los 5 segundos exactos que pediste.
        delay(2500L)

        // 4. Cambiamos de pantalla
        onNavigateToHome()
    }

    // Animación paralela para que el logo vaya apareciendo (Fade In)
    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500)
        )
    }

    // Interfaz visual del Splash Screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A192F)), // Fondo azul marino
        contentAlignment = Alignment.Center
    ) {
        // Aplicamos las animaciones a esta columna central
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
        ) {
            // LOGO: Un círculo dorado con el ícono de Play en azul marino
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .background(Color(0xFFFFC107), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Logo Alu-Cine",
                    tint = Color(0xFF0A192F),
                    modifier = Modifier.size(90.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // NOMBRE DE LA APP
            Text(
                text = "ALU-CINE",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ESLOGAN
            Text(
                text = "Tu pase a la diversión \uD83C\uDF7F",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF8892B0)
            )
        }
    }
}