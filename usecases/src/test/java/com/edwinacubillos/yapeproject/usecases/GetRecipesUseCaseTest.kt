package com.edwinacubillos.yapeproject.usecases

import com.edwinacubillos.yapeproject.data.repository.impl.RecipesRepositoryImpl
import com.edwinacubillos.yapeproject.usecases.recipes.GetRecipesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetRecipesUseCaseTest {

    @Test
    fun `Invoke calls recipes repository`(): Unit = runBlocking {
        val recipesRepository = mock<RecipesRepositoryImpl>()

        val getRecipesUseCase = GetRecipesUseCase(recipesRepository)

        getRecipesUseCase()

        verify(recipesRepository).getRecipes()
    }


}