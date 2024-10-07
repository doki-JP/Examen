package com.example.examen.data.models

data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val imageUrl: String
)

data class CharacterResponse(
    val data: List<Character>,
    val page: Int,
    val totalPages: Int
)