package com.example.cineapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MovieDetailScreen(movieId: Int, onNavigateToBook: (Int) -> Unit) {
    val movie = sampleMovies.find { it.id == movieId }

    if (movie != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0A192F))
                .verticalScroll(rememberScrollState())
        ) {
            // Usamos Image y painterResource aquí también
            Image(
                painter = painterResource(id = movie.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.headlineMedium, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Boleto: $${movie.price}", style = MaterialTheme.typography.titleMedium, color = Color(0xFFFFC107))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Sinopsis", style = MaterialTheme.typography.titleLarge, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = movie.synopsis, style = MaterialTheme.typography.bodyLarge, color = Color(0xFF8892B0))
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { onNavigateToBook(movie.id) },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
                ) {
                    Text("Seleccionar Asientos", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}