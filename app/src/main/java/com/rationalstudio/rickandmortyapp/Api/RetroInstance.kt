package com.rationalstudio.rickandmortyapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object{
        const val BASE_URL="https://rickandmortyapi.com/api/"
        fun getRetrofiInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}