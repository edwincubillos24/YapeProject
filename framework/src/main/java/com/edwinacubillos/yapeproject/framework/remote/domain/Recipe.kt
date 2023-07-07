package com.edwinacubillos.yapeproject.framework.remote.domain

import com.google.gson.annotations.SerializedName

data class Recipe(

    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String,
    @SerializedName("url_picture")
    val urlPicture: String

)
