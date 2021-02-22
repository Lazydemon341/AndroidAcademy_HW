package com.avvlas.androidacademyhomework.di

import com.avvlas.androidacademyhomework.data.remote.retrofit.MoviesApiService
import com.avvlas.androidacademyhomework.config.AppConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class NetworkModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(ApiKeyInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(AppConfig.API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(httpClient)
        .build()

    val api: MoviesApiService by lazy { retrofit.create() }
}

private class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val urlBuilder = origin.url.newBuilder()
        val url = urlBuilder
            .addQueryParameter("api_key", AppConfig.API_KEY)
            .build()
        val request = origin
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}