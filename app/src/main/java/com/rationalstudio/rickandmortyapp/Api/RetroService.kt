package com.rationalstudio.rickandmortyapp.Api

import com.rationalstudio.rickandmortyapp.Models.RickAndMortyModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    companion object{
        const val BASE_URL="https://rickandmortyapi.com/api/"
    }
    @GET("character")
    suspend fun getData(@Query("page") page: Int ): Call<RickAndMortyModel>
}