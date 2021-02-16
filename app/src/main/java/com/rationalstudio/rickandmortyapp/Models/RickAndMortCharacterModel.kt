package com.rationalstudio.rickandmortyapp.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class RickAndMortCharacterModel (
        val name: String?,
        val status: String?,
        val image: String?
        ): Parcelable {
        val baseUrl get() = "https://rickandmortyapi.com/api/"
}