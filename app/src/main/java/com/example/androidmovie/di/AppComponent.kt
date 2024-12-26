package com.example.androidmovie.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.androidmovie.App
import com.example.androidmovie.di.modules.DomainModule
import com.example.androidmovie.di.modules.FeatureDepsModule
import com.example.movie.presentation.di.MovieDeps
import com.example.popularmovies.presentation.di.PopularMoviesDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@Component(
    modules = [
        AppModule::class,
        DomainModule::class,
        FeatureDepsModule::class
    ]
)

@AppScope
interface AppComponent :
    PopularMoviesDeps,
    MovieDeps {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun connectivityManager(connectivityManager: ConnectivityManager): Builder

        fun build(): AppComponent
    }

    fun inject(appApplication: App)
}

@Module
class AppModule {
    @AppScope
    @Provides
    fun provideContext(
        application: Application
    ): Context = application.applicationContext
}
