package com.edwinacubillos.yapeproject.data.repository.impl

import com.edwinacubillos.yapeproject.data.repository.RecipesRepository
import com.edwinacubillos.yapeproject.data.source.RemoteRecipesDataSource
import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.yapeproject.domain.remote.WrappedResponse
import com.edwinacubillos.yapeproject.domain.remote.data.LocalRecipe

class RecipesRepositoryImpl(
    private var remoteRecipesDataSource: RemoteRecipesDataSource
) : RecipesRepository {

    override suspend fun getRecipes(): ResourceRemote<WrappedResponse<List<LocalRecipe>>> =
        remoteRecipesDataSource.getRecipes()

}
