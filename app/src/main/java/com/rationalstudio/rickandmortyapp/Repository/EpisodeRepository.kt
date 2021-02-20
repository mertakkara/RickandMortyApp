package com.rationalstudio.rickandmortyapp.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.rationalstudio.rickandmortyapp.Api.RetroService
import com.rationalstudio.rickandmortyapp.CharacterPagingSource
import com.rationalstudio.rickandmortyapp.EpisodePagingSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class EpisodeRepository @Inject constructor(private val characterApi:RetroService) {
    fun getAllEpisodes()=
            Pager(
                config= PagingConfig(
                    pageSize = 5,
                    maxSize = 20,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {EpisodePagingSource(characterApi,null)}
            ).liveData


    fun getSearchEpisode(name:String)=
        Pager(
            config= PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {EpisodePagingSource(characterApi,name)}
        ).liveData



}