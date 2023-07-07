package com.edwinacubillos.yapeproject.framework.remote.mappers

import com.edwinacubillos.domain.remote.Status
import com.edwinacubillos.yapeproject.domain.remote.WrappedResponse
import com.edwinacubillos.yapeproject.domain.remote.data.LocalRecipe
import com.edwinacubillos.yapeproject.framework.remote.domain.RecipesList

class RemoteRecipeMapper {

    fun getRecipes(recipesList: RecipesList): WrappedResponse<List<LocalRecipe>> {
        return WrappedResponse(
            status = Status.Success,
            data = recipesList.map {
                LocalRecipe(
                    ingredients = it.ingredients,
                    instructions = it.instructions,
                    latitude = it.latitude,
                    longitude = it.longitude,
                    name = it.name,
                    originCountry = it.originCountry,
                    urlPicture = it.urlPicture

                )
            }
        )
    }
}
