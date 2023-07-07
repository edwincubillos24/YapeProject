package com.edwinacubillos.yapeproject.data.server.source

import com.edwinacubillos.yapeproject.data.source.RemoteRecipesDataSource
import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.yapeproject.domain.remote.WrappedResponse
import com.edwinacubillos.yapeproject.domain.remote.data.LocalRecipe
import com.edwinacubillos.yapeproject.framework.remote.api.APIService
import com.edwinacubillos.yapeproject.framework.remote.mappers.RemoteRecipeMapper
import com.edwinacubillos.yapeproject.data.server.WrappedResponseHandler

class RemoteRecipesImplDataSource(
    private val apiService: APIService,
    private val remoteRecipeMapper: RemoteRecipeMapper,
    private val wrappedResponseHandler: WrappedResponseHandler
) : RemoteRecipesDataSource {

    override suspend fun getRecipes(): ResourceRemote<WrappedResponse<List<LocalRecipe>>> {
        return wrappedResponseHandler.handleSuccess(remoteRecipeMapper.getRecipes(apiService.getRecipes()))
    }

}
