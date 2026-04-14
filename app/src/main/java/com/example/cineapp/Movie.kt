package com.example.cineapp

import com.example.cineapp.R

data class Movie(
    val id: Int,
    val title: String,
    val synopsis: String,
    val price: Double,
    val imageRes: Int,
    val availableTimes: List<String> = listOf("14:00", "17:30", "21:00"),
    // Mapa: "Hora" -> Lista de asientos ocupados
    val occupiedByTime: MutableMap<String, MutableList<String>> = mutableMapOf()
)

val sampleMovies = listOf(
    Movie(1, "Dune: Parte Dos", "Paul Atreides se une a los Fremen.", 85.0, R.drawable.dune,
        occupiedByTime = mutableMapOf("14:00" to mutableListOf("A1", "A2"))),
    Movie(2, "Kung Fu Panda 4", "Po enfrenta a la Camaleona.", 70.0, R.drawable.panda),
    Movie(3, "Godzilla y Kong", "Titanes se enfrentan.", 80.0, R.drawable.godzilla),
    Movie(4, "Oppenheimer", "Historia de la bomba atómica.", 90.0, R.drawable.oppenheimer,
        occupiedByTime = mutableMapOf("21:00" to mutableListOf("D1", "D2"))),
    Movie(5, "Spider-Man", "Aventura en el multiverso.", 75.0, R.drawable.spiderman),
    Movie(6, "Super Mario Bros", "Reino Champiñón.", 70.0, R.drawable.mario),
    Movie(7, "The Batman", "Justicia en Gotham.", 85.0, R.drawable.batman),
    Movie(8, "Interstellar", "Viaje espacial.", 80.0, R.drawable.interestelar)
)