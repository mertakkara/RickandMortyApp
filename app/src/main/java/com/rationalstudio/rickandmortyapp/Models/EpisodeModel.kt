package com.rationalstudio.rickandmortyapp.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class EpisodeModel (
    @SerializedName("id")
    val kid : String,
    val name: String,
    val episode: String
    ): Parcelable {
        val baseUrl get() = "https://rickandmortyapi.com/api/"
}