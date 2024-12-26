package com.example.androidmovie

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.androidmovie.di.DaggerAppComponent
import com.example.core.dependency.DepsMap
import com.example.core.dependency.HasDependencies
import javax.inject.Inject

class App : Application(), HasDependencies {

    @Inject
    override lateinit var depsMap: DepsMap

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .connectivityManager(getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .build().
            inject(this)
    }
}
