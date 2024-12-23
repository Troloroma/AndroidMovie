package com.example.androidmovie.di.modules

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
    ]
)
class DataModule {
}