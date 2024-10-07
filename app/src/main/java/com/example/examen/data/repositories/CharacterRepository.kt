package com.example.examen.data.repository

import android.util.Log
import com.example.examen.data.models.CharacterResponse
import com.example.examen.data.network.DragonBallApiService

class CharacterRepository(private val apiService: DragonBallApiService) {
    suspend fun getCharacters(page: Int): CharacterResponse {
        var result = apiService.getCharacters(1)
        Log.d("flow", "getCharacters: $result")
        return apiService.getCharacters(page)
    }
}