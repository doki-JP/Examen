package com.example.examen.data.models

data class Character(
    val id: Int,
    val name: String,
    val race: String,
    val gender: String,
    val image: String,
    val ki: String,
    val maxKi:String,
    val description:String,
    val affiliation:String
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
