package com.example.data.remote

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


class RetrofitClient @Inject constructor(private val retrofit:Retrofit) {
    fun getService(baseUrl: String): APiServices {
        return retrofit.newBuilder().baseUrl(baseUrl).build().create(APiServices::class.java)
    }
}