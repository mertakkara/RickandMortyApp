package com.rationalstudio.rickandmortyapp.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class RickAndMortyCharacterModel (
        @SerializedName("id")
        val kid : String,
        val name: String,
        val status: String,
        val image: String
        ): Parcelable {
        val baseUrl get() = "https://rickandmortyapi.com/api/"
}