package com.example.golinkproject.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class ApiErrorHandler : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            // Обработка ошибок здесь
            when (response.code) {
                400 -> {
                    // Обработка ошибки 400
                }
                500 -> {
                    // Обработка ошибки 500
                }
                // Другие коды ошибок...
            }
        }

        return response
    }

}