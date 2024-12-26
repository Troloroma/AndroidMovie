package com.example.core.navigation

sealed class NavigationFlow {
    object PopularMoviesFlow : NavigationFlow()
    class MovieFlow(val movieId: String) : NavigationFlow()
}