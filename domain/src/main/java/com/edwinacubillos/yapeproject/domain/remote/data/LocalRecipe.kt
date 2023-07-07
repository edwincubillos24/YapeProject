package com.edwinacubillos.yapeproject.domain.remote.data

import java.io.Serializable

data class LocalRecipe(
    val ingredients: List<String>,
    val instructions: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val originCountry: String,
    val urlPicture: String
) : Serializable
