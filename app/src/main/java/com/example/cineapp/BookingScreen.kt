package com.example.cineapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(movie: Movie, onBookingSuccess: (Int, String, String, Float) -> Unit) {
    var ticketCount by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf(movie.availableTimes[0]) } // Hora por defecto
    val selectedSeats = remember { mutableStateListOf<String>() }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val rows = listOf("A", "B", "C", "D")
    val allSeats = rows.flatMap { row -> (1..5).map { col -> "$row$col" } }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color(0xFF0A192F)
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp).fillMaxSize()) {
            Text("Reserva: ${movie.title}", color = Color.White, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            // SELECTOR DE HORARIOS
            Text("Selecciona el horario:", color = Color.White, style = MaterialTheme.typography.bodyMedium)
            LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
                items(movie.availableTimes) { time ->
                    val isTimeSelected = selectedTime == time
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isTimeSelected) Color(0xFFFFC107) else Color(0xFF112240))
                            .clickable {
                                selectedTime = time
                                selectedSeats.clear() // Limpiar selección al cambiar de hora
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(time, color = if (isTimeSelected) Color.Black else Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = ticketCount,
                onValueChange = {
                    ticketCount = it
                    selectedSeats.clear()
                },
                label = { Text("Cantidad de boletos", color = Color(0xFF8892B0)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFFC107),
                    unfocusedBorderColor = Color(0xFF8892B0),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Pantalla y Asientos (Igual que antes, pero filtrando por selectedTime)
            Box(modifier = Modifier.fillMaxWidth().height(30.dp).clip(RoundedCornerShape(8.dp)).background(Color.Gray), contentAlignment = Alignment.Center) {
                Text("PANTALLA", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            val maxTickets = ticketCount.toIntOrNull() ?: 0
            val occupiedNow = movie.occupiedByTime[selectedTime] ?: listOf<String>()

            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(allSeats) { seat ->
                    val isOccupied = occupiedNow.contains(seat)
                    val isSelected = selectedSeats.contains(seat)

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                when {
                                    isOccupied -> Color.Gray
                                    isSelected -> Color(0xFFFFC107)
                                    else -> Color(0xFF112240)
                                }
                            )
                            .clickable(enabled = !isOccupied) {
                                if (maxTickets > 0) {
                                    if (isSelected) selectedSeats.remove(seat)
                                    else if (selectedSeats.size < maxTickets) selectedSeats.add(seat)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(seat, color = if (isSelected || isOccupied) Color.Black else Color.White)
                    }
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)),
                onClick = {
                    if (maxTickets <= 0) {
                        coroutineScope.launch { snackbarHostState.showSnackbar("Ingresa una cantidad válida.") }
                    } else if (selectedSeats.size < maxTickets) {
                        coroutineScope.launch { snackbarHostState.showSnackbar("Faltan asientos por seleccionar.") }
                    } else {
                        val total = (maxTickets * movie.price).toFloat()
                        onBookingSuccess(movie.id, selectedTime, selectedSeats.joinToString("-"), total)
                    }
                }
            ) {
                Text("Confirmar Compra", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}