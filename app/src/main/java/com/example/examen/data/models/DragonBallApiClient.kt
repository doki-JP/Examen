package com.example.examen.data.models

import com.example.examen.data.network.DragonBallApiService
import com.example.examen.data.network.NetworkModuleDI

class DragonBallApiClient {
    private lateinit var apiService: DragonBallApiService

    suspend fun getCharacters(page: Int): CharacterResponse {
        apiService = NetworkModuleDI.api
        return try{
            apiService.getCharacters(page)
        } catch(err: Exception){
            throw err
        }
    }
}