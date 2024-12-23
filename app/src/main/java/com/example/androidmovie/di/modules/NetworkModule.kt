package com.example.androidmovie.di.modules

import android.net.ConnectivityManager
import com.example.data.movies_api_provider.ApiProvider
import com.example.network.network.ApiConst
import com.example.network.network.api.Api
import com.example.network.network.provider.NetworkProvider
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun provideNetworkProvider(
        connectivityManager: ConnectivityManager
    ): NetworkProvider = NetworkProvider(connectivityManager)

    @Provides
    fun provideApiProvider(
        networkProvider: NetworkProvider
    ): ApiProvider = ApiProvider(networkProvider)

    @Provides
    fun provideApi(
        provider: ApiProvider
    ) : Api = provider.provideNetwork(baseUrl = "ApiConst.HOST_API")
}
