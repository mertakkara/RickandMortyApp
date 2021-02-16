package com.rationalstudio.rickandmortyapp.Repository

import com.rationalstudio.rickandmortyapp.Api.RetroService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterRepository @Inject constructor(private val CharacterApi:RetroService) {
}