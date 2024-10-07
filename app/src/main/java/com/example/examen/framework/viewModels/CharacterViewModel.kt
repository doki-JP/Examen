package com.example.examen.presentation

import androidx.lifecycle.*
import com.example.examen.data.models.Character
import com.example.examen.domain.GetCharacters
import kotlinx.coroutines.launch

class CharacterViewModel(private val getCharactersUseCase: GetCharacters) : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>() // To observe error messages
    val error: LiveData<String?> get() = _error

    private var currentPage = 1
    private var allCharacters = mutableListOf<Character>()  // To keep track of all characters

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null  // Reset error state
            try {
                val response = getCharactersUseCase(currentPage)
                response.data?.let { newCharacters ->
                    allCharacters.addAll(newCharacters)
                    _characters.value = allCharacters
                }
                currentPage++  // Increment the page number for future fetches
            } catch (e: Exception) {
                _error.value = "Failed to load characters: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchNextPage() {
        if (!_loading.value!!) {  // Only fetch if not currently loading
            fetchCharacters()
        }
    }
}
