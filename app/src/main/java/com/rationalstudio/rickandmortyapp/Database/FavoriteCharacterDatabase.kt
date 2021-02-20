package com.rationalstudio.rickandmortyapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rationalstudio.rickandmortyapp.Models.FavoriteCharacter


@Database(
        entities = [FavoriteCharacter::class],
        version = 1
)
abstract class FavoriteCharacterDatabase:RoomDatabase() {
    abstract fun getFavoriteCharacterDao(): FavoriteCharacterDao
}