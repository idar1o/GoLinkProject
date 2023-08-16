package com.example.golinkproject.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InitMainApi {
    fun createService(): MainAPI {


        val client = OkHttpClient.Builder()
            .addInterceptor(ApiErrorHandler())
            .build()

        val retrofit= Retrofit.Builder()
            .baseUrl("http://178.47.141.212:5000").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi=retrofit.create(MainAPI::class.java)

        return mainApi
    }
}