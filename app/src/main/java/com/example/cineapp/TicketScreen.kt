package com.example.cineapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TicketScreen(movieId: Int, time: String, seats: String, total: Float, onNavigateHome: () -> Unit) {
    val movie = sampleMovies.find { it.id == movieId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A192F))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("¡Compra Exitosa!", style = MaterialTheme.typography.headlineMedium, color = Color(0xFFFFC107), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        // Diseño del "Ticket"
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // Color papel claro
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("ALU-CINE", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black, color = Color.Black)
                Text("Tu pase a la diversión", style = MaterialTheme.typography.bodySmall, color = Color.Gray)

                Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.Gray)

                movie?.let {
                    Image(
                        painter = painterResource(id = it.imageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(120.dp).clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = it.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.Black, textAlign = TextAlign.Center)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // NUEVO: Fila para el horario
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Horario:", color = Color.DarkGray, fontWeight = FontWeight.Bold)
                    Text(time, color = Color.Black, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Asientos:", color = Color.DarkGray, fontWeight = FontWeight.Bold)
                    Text(seats.replace("-", ", "), color = Color.Black, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total Pagado:", color = Color.DarkGray, fontWeight = FontWeight.Bold)
                    Text("$$total", color = Color(0xFF006400), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Black) // Verde oscuro
                }

                Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.Gray)
                Text("¡Disfruta tu función!", style = MaterialTheme.typography.bodyMedium, color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onNavigateHome,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
        ) {
            Text("Volver a Cartelera", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}