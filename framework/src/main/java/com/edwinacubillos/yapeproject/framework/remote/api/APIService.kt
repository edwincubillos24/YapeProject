package com.edwinacubillos.yapeproject.framework.remote.api

import com.edwinacubillos.yapeproject.framework.remote.domain.RecipesList
import retrofit2.http.GET

interface APIService {

    @GET("get_recipes")
    suspend fun getRecipes(): RecipesList

}
