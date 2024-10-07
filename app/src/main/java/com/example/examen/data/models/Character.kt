package com.example.examen.data.models

data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val imageUrl: String
)

data class CharacterResponse(
    val items: List<Character>,  
    val meta: Meta
)

data class Meta(
    val totalItems: Int,
    val itemCount: Int,
    val itemsPerPage: Int,
    val totalPages: Int,
    val currentPage: Int
)
