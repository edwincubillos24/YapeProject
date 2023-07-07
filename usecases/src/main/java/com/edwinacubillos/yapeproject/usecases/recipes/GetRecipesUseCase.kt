package com.edwinacubillos.yapeproject.usecases.recipes

import com.edwinacubillos.yapeproject.data.repository.RecipesRepository
import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.yapeproject.domain.remote.WrappedResponse
import com.edwinacubillos.yapeproject.domain.remote.data.LocalRecipe

class GetRecipesUseCase(private val recipesRepository: RecipesRepository) {

    suspend operator fun invoke(): ResourceRemote<WrappedResponse<List<LocalRecipe>>> =
        recipesRepository.getRecipes()

}
