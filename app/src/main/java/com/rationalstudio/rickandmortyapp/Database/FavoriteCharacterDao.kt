package com.rationalstudio.rickandmortyapp.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rationalstudio.rickandmortyapp.Models.FavoriteCharacter


@Dao
interface FavoriteCharacterDao {

    @Insert
    suspend fun addToFavorite(favoriteCharacter: FavoriteCharacter)

    @Query("SELECT * FROM favorite_character")
    fun getFavoriteCharacter(): LiveData<List<FavoriteCharacter>>

    @Query("SELECT count(*) FROM favorite_character WHERE favorite_character.cid = :id")
    suspend fun checkCharacter(id: String): Int

    @Query("DELETE FROM favorite_character WHERE favorite_character.cid = :id" )
    suspend fun removeFromFavorite(id: String) : Int
}