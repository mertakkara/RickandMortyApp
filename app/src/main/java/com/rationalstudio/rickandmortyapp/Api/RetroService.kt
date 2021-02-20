package com.rationalstudio.rickandmortyapp.Api

import com.rationalstudio.rickandmortyapp.Models.EpisodeModel
import com.rationalstudio.rickandmortyapp.Models.EpisodeResultModel
import com.rationalstudio.rickandmortyapp.Models.RickAndMortyModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    companion object{
        const val BASE_URL="https://rickandmortyapi.com/api/"
    }
    @GET("character/")
    suspend fun getData(@Query("page") page: Int ): RickAndMortyModel

    @GET("character/")
    suspend fun searchCharacter(@Query("name") name: String ,@Query("page") page: Int ): RickAndMortyModel

    @GET("episode/")
    suspend fun getEpisode(@Query("page") page: Int ): EpisodeResultModel

    @GET("episode/")
    suspend fun searchEpisode(@Query("name") name: String ,@Query("page") page: Int ): EpisodeResultModel

}