package com.example.examen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examen.domain.GetCharacters

class CharacterViewModelFactory(private val getCharactersUseCase: GetCharacters) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(getCharactersUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}