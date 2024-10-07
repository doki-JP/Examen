package com.example.examen.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDI {
    private const val BASE_URL = "https://dragonball-api.com/"

    val api: DragonBallApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DragonBallApiService::class.java)
    }
}