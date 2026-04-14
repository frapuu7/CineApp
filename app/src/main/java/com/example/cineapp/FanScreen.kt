package com.example.cineapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A192F)) // Fondo azul marino
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tarjeta Fan VIP ",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFFFFC107),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Falso Código QR (Dibujado con cajitas para no usar librerías externas)
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.size(50.dp).background(Color.Black))
                    Box(modifier = Modifier.size(50.dp).background(Color.Black))
                }
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.size(30.dp).background(Color.Black))
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(modifier = Modifier.size(40.dp).background(Color.Black))
                }
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.size(50.dp).background(Color.Black))
                    Box(modifier = Modifier.size(20.dp).background(Color.Black))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Escanea en dulcería o taquilla", color = Color.White, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(32.dp))

        // Lista de Promociones
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF112240)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Beneficios Exclusivos:",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                PromoItem("🍿 Lunes de Palomitas Jumbo a mitad de precio.")
                PromoItem("🎟️ 2x1 en boletos todos los martes.")
                PromoItem("🥤 Refill gratis en tu vaso promocional.")
                PromoItem("⭐ Fila express VIP en dulcería.")
            }
        }
    }
}

// Sub-componente para hacer la lista más limpia
@Composable
fun PromoItem(text: String) {
    Text(
        text = text,
        color = Color(0xFF8892B0),
        modifier = Modifier.padding(vertical = 8.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}