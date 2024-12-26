package com.example.androidmovie.di.modules

import com.example.androidmovie.di.feature_deps.MovieDepsModule
import com.example.androidmovie.di.feature_deps.PopularMoviesDepsModule
import dagger.Module

@Module(
    includes = [
        PopularMoviesDepsModule::class,
        MovieDepsModule::class
    ]
)
interface FeatureDepsModule
