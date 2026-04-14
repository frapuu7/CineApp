package com.example.cineapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MovieListScreen(movies: List<Movie>, onMovieSelected: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF0A192F))) {
        Text(
            text = "Alu-Cine \uD83C\uDF7F",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            // Aquí usamos la lista 'movies' que recibe la función
            items(movies) { movie ->
                MovieCard(
                    movie = movie,
                    onClick = { onMovieSelected(movie.id) }
                )
            }
        }
    }
}