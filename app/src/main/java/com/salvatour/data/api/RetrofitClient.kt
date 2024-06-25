package com.salvatour.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api-app-2-u1tx.onrender.com"

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()

        retrofit.create(ApiService::class.java)
    }
}