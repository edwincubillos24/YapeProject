package com.edwinacubillos.yapeproject.data

import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.domain.remote.Status
import com.edwinacubillos.yapeproject.data.repository.impl.RecipesRepositoryImpl
import com.edwinacubillos.yapeproject.data.source.RemoteRecipesDataSource
import com.edwinacubillos.yapeproject.domain.remote.WrappedResponse
import com.edwinacubillos.yapeproject.domain.remote.data.LocalRecipe
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class RecipesRepositoryImplTest {

    @Mock
    lateinit var remoteRecipesDataSource: RemoteRecipesDataSource

    private lateinit var recipesRepository: RecipesRepositoryImpl

    @Before
    fun setUp() {
        remoteRecipesDataSource = mock(RemoteRecipesDataSource::class.java)
        recipesRepository = RecipesRepositoryImpl(remoteRecipesDataSource)
    }

    @Test
    fun `Assert it works`() {
        Assert.assertTrue(true)
    }

    @Test
    fun `get Recipes from server`(): Unit = runBlocking {
        //Arrange
        val expectedData = listOf(
            sampleRecipe,
            sampleRecipe
        )
        val wrappedResponse = WrappedResponse(Status.Success, expectedData)
        val resourceRemote = ResourceRemote.Success(Status.Success, wrappedResponse)

        `when`(remoteRecipesDataSource.getRecipes()).thenReturn(resourceRemote)

        // Act
        val result = recipesRepository.getRecipes()

        // Assert
        assertEquals(Status.Success, result.status)
        assertEquals(wrappedResponse, result.data)
    }
}

private val sampleRecipe = LocalRecipe(
    ingredients = arrayListOf("Carne de res", "Sal", "Pimienta", "Chimichurri", "Vegetales asados"),
    instructions = "1. Sazona la carne de res con sal y pimienta al gusto. 2. Prepara un fuego o enciende la parrilla. 3. Coloca la carne en la parrilla y cocina a la temperatura deseada. 4. Acompaña con chimichurri y vegetales asados.",
    latitude = -34.603722,
    longitude = -58.381592,
    name = "Asado",
    originCountry = "Argentina",
    urlPicture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrDgIkZIFEuzNorjNQN3jZDQqt-x9x8UbGHQ&usqp=CAU"
)
