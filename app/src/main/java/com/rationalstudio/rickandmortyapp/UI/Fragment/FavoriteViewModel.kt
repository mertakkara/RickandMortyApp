package com.rationalstudio.rickandmortyapp.UI.Fragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.rationalstudio.rickandmortyapp.Repository.FavoriteCharacterRepository

class FavoriteViewModel @ViewModelInject constructor(
        private val repository: FavoriteCharacterRepository
) : ViewModel(){
    val characters = repository.getFavoriteCharacter()
}