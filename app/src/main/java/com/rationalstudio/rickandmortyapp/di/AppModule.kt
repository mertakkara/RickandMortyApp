package com.rationalstudio.rickandmortyapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rationalstudio.rickandmortyapp.Api.RetroService
//import com.rationalstudio.rickandmortyapp.FavoriteCharacterDatabase
//import com.rationalstudio.rickandmortyapp.FavoriteCharacterDatabase
//import com.rationalstudio.rickandmortyapp.Models.FavoriteCharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(RetroService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit): RetroService =
        retrofit.create(RetroService::class.java)


}