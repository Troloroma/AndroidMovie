package com.example.network.network.provider

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkProvider(
    private val connectivityManager: ConnectivityManager
) {
    private var gson = GsonBuilder().setLenient().create()
    private val timeout: Long = 60

    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(timeout, TimeUnit.SECONDS)
        readTimeout(timeout, TimeUnit.SECONDS)
        writeTimeout(timeout, TimeUnit.SECONDS)
    }.build()

    fun <T> provideRetrofit(
        clazz: Class<T>,
        url: String
    ): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(clazz)
    }

    fun isInternetAvailable(): Boolean {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNetworkManager =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        val result = when {
            actNetworkManager.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNetworkManager.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNetworkManager.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }
}
