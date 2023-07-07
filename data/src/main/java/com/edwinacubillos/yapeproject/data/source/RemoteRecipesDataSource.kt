package com.edwinacubillos.yapeproject.data.source

import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.yapeproject.domain.remote.WrappedResponse
import com.edwinacubillos.yapeproject.domain.remote.data.LocalRecipe

interface RemoteRecipesDataSource {

    suspend fun getRecipes(): ResourceRemote<WrappedResponse<List<LocalRecipe>>>

}
