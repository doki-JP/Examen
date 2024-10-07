package com.example.examen.data.network

import com.example.examen.data.models.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DragonBallApiService {
    @GET("api/characters")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse
}