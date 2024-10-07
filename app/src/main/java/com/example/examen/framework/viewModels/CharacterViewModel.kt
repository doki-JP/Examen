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
            try {
                val characterResponse = getCharactersUseCase(currentPage)
                allCharacters.addAll(characterResponse.items)
                _characters.value = allCharacters
                currentPage++
            } catch (e: Exception) {
                _error.value = e.message
            }
            _loading.value = false
        }
    }

    fun fetchNextPage() {
        if (!_loading.value!!) {  // Only fetch if not currently loading
            fetchCharacters()
        }
    }
}
