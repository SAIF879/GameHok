package com.example.gamehok.data.model

import com.google.gson.annotations.SerializedName


data class GameData (

    @SerializedName("id"        ) var id        : Int?    = null,
    @SerializedName("gameName"  ) var gameName  : String? = null,
    @SerializedName("imagePath" ) var imagePath : String? = null

)