package com.rationalstudio.rickandmortyapp.UI.Fragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.rationalstudio.rickandmortyapp.Repository.CharacterRepository

class CharacterViewModel @ViewModelInject constructor(private val repository: CharacterRepository):ViewModel() {
    val characters = repository.getAllCharacters()
}