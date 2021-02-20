package com.rationalstudio.rickandmortyapp.Models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Entity(tableName = "favorite_character")
@Parcelize
data class FavoriteCharacter(
        @SerializedName("id")
        val cid : String,
        val name: String,
        val status: String,
        @SerializedName("image")
        val cimage: String
):Serializable, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var kid:Int = 0
    val baseUrl get() = "https://rickandmortyapi.com/api/"
}