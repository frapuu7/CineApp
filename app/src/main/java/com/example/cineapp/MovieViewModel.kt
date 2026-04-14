package com.example.cineapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {
    val movies = mutableStateListOf<Movie>().apply { addAll(sampleMovies) }

    fun addOccupiedSeats(movieId: Int, time: String, newSeats: List<String>) {
        val index = movies.indexOfFirst { it.id == movieId }
        if (index != -1) {
            val movie = movies[index]
            // Si la hora no existe en el mapa, la creamos
            if (!movie.occupiedByTime.containsKey(time)) {
                movie.occupiedByTime[time] = mutableListOf()
            }
            movie.occupiedByTime[time]?.addAll(newSeats)
            movies[index] = movie.copy()
        }
    }
}