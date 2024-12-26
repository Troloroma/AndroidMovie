package com.example.core.navigation

import androidx.navigation.NavController

class Navigator {
    lateinit var navController: NavController
    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        is NavigationFlow.PopularMoviesFlow -> navController.navigate(
            MainNavGraphDirections.actionPopularMovieFeature()
        )

        is NavigationFlow.MovieFlow -> navController.navigate(
            MainNavGraphDirections.actionMovieFeature(movieId = navigationFlow.movieId)
        )
    }
}