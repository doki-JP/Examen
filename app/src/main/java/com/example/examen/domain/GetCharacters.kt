package com.example.examen.domain

import com.example.examen.data.models.CharacterResponse
import com.example.examen.data.repository.CharacterRepository

class GetCharacters(private val repository: CharacterRepository) {
    suspend operator fun invoke(page: Int): CharacterResponse {
        return repository.getCharacters(page)
    }
}