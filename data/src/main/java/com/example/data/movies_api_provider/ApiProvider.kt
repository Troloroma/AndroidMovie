package com.example.data.movies_api_provider

import com.example.network.network.api.Api
import com.example.network.network.provider.NetworkProvider

class ApiProvider (
    private val provider: NetworkProvider
){
    fun provideNetwork(baseUrl: String): Api = provider.provideRetrofit(Api::class.java, baseUrl)
}
