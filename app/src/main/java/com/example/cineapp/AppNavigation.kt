package com.example.cineapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val movieViewModel: MovieViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute == "movie_list" || currentRoute == "fan_screen"

    Scaffold(
        containerColor = Color(0xFF0A192F),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color(0xFF112240),
                    contentColor = Color.White
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Cartelera") },
                        label = { Text("Cartelera") },
                        selected = currentRoute == "movie_list",
                        onClick = {
                            navController.navigate("movie_list") {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = Color(0xFFFFC107),
                            indicatorColor = Color(0xFFFFC107),
                            unselectedIconColor = Color(0xFF8892B0),
                            unselectedTextColor = Color(0xFF8892B0)
                        )
                    )

                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Star, contentDescription = "Fan") },
                        label = { Text("Fan") },
                        selected = currentRoute == "fan_screen",
                        onClick = {
                            navController.navigate("fan_screen") {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = Color(0xFFFFC107),
                            indicatorColor = Color(0xFFFFC107),
                            unselectedIconColor = Color(0xFF8892B0),
                            unselectedTextColor = Color(0xFF8892B0)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("splash") {
                SplashScreen(
                    onNavigateToHome = {
                        navController.navigate("movie_list") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                )
            }

            composable("movie_list") {
                MovieListScreen(
                    movies = movieViewModel.movies,
                    onMovieSelected = { movieId ->
                        navController.navigate("movie_detail/$movieId")
                    }
                )
            }

            composable("fan_screen") {
                FanScreen()
            }

            composable(
                route = "movie_detail/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                MovieDetailScreen(
                    movieId = movieId,
                    onNavigateToBook = { id -> navController.navigate("booking/$id") }
                )
            }

            // RUTA DE RESERVA: Pasa los 4 datos al éxito (id, hora, asientos, total)
            composable(
                route = "booking/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                val movie = movieViewModel.movies.find { it.id == movieId }

                movie?.let {
                    BookingScreen(
                        movie = it,
                        onBookingSuccess = { id, time, seats, total ->
                            movieViewModel.addOccupiedSeats(id, time, seats.split("-"))
                            navController.navigate("ticket/$id/$time/$seats/$total")
                        }
                    )
                }
            }

            // NUEVA RUTA DE TICKET: Ahora recibe el parámetro {time}
            composable(
                route = "ticket/{movieId}/{time}/{seats}/{total}",
                arguments = listOf(
                    navArgument("movieId") { type = NavType.IntType },
                    navArgument("time") { type = NavType.StringType }, // <-- Horario agregado
                    navArgument("seats") { type = NavType.StringType },
                    navArgument("total") { type = NavType.FloatType }
                )
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                val time = backStackEntry.arguments?.getString("time") ?: ""
                val seats = backStackEntry.arguments?.getString("seats") ?: ""
                val total = backStackEntry.arguments?.getFloat("total") ?: 0f

                TicketScreen(
                    movieId = movieId,
                    time = time,
                    seats = seats,
                    total = total,
                    onNavigateHome = {
                        navController.popBackStack("movie_list", inclusive = false)
                    }
                )
            }
        }
    }
}