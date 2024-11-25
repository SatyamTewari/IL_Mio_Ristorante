package com.example.ilmioristorante.data.remote

import com.example.ilmioristorante.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder().addHeader("Authorization", "Client-ID ${BuildConfig.API_KEY}").build()
        return chain.proceed(newRequest)
    }
}