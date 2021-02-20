package com.rationalstudio.rickandmortyapp.UI.Fragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.rationalstudio.rickandmortyapp.Models.FavoriteCharacter
import com.rationalstudio.rickandmortyapp.Models.RickAndMortyCharacterModel
import com.rationalstudio.rickandmortyapp.Repository.FavoriteCharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel  @ViewModelInject constructor(
        private val repository : FavoriteCharacterRepository
) : ViewModel(){
    fun addToFavorite(character: RickAndMortyCharacterModel){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFavorite(
                    FavoriteCharacter(
                            character.kid,
                            character.name,
                            character.status,
                            character.image
                    )
            )
        }
    }

    suspend fun checkCharacter(id: String) = repository.checkCharacter(id)

    fun removeFromFavorite(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeFromFavorite(id)
        }
    }

}