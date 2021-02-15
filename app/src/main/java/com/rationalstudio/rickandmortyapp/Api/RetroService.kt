package com.rationalstudio.rickandmortyapp.Api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("character")
    fun getData(@Query("page") page: Int ): Call<RickAndMortyModel>
}