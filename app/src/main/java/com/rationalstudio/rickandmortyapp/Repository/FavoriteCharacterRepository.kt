package com.rationalstudio.rickandmortyapp.Repository

import com.rationalstudio.rickandmortyapp.Database.FavoriteCharacterDao
import com.rationalstudio.rickandmortyapp.Models.FavoriteCharacter
import javax.inject.Inject

class FavoriteCharacterRepository@Inject constructor(
        private val favoriteCharacterDao: FavoriteCharacterDao
) {
    suspend fun addToFavorite(favoriteCharacter: FavoriteCharacter) = favoriteCharacterDao.addToFavorite(favoriteCharacter)
    fun getFavoriteCharacter() = favoriteCharacterDao.getFavoriteCharacter()
    suspend fun checkCharacter(id: String) = favoriteCharacterDao.checkCharacter(id)
    suspend fun removeFromFavorite(id: String) {
        favoriteCharacterDao.removeFromFavorite(id)
    }

}