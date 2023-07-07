package com.edwinacubillos.yapeproject.data.repository

import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.yapeproject.domain.remote.WrappedResponse
import com.edwinacubillos.yapeproject.domain.remote.data.LocalRecipe

interface RecipesRepository {

    suspend fun getRecipes(): ResourceRemote<WrappedResponse<List<LocalRecipe>>>

}
