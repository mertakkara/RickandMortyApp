package com.rationalstudio.rickandmortyapp.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.rationalstudio.rickandmortyapp.Api.RetroService
import com.rationalstudio.rickandmortyapp.CharacterPagingSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterRepository @Inject constructor(private val characterApi:RetroService) {
    fun getAllCharacters()=
            Pager(
                config= PagingConfig(
                    pageSize = 5,
                    maxSize = 20,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {CharacterPagingSource(characterApi)}
            ).liveData


}