package com.edwinacubillos.yapeproject.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.yapeproject.domain.remote.WrappedResponse
import com.edwinacubillos.yapeproject.domain.remote.data.LocalRecipe
import com.edwinacubillos.yapeproject.usecases.recipes.GetRecipesUseCase
import kotlinx.coroutines.launch

class ListViewModel(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _recipesLoaded: MutableLiveData<ResourceRemote<WrappedResponse<List<LocalRecipe>>>?> = MutableLiveData()
    val recipesLoaded: LiveData<ResourceRemote<WrappedResponse<List<LocalRecipe>>>?> = _recipesLoaded

    private val _recipesFilterByIngredient: MutableLiveData<ArrayList<LocalRecipe>> = MutableLiveData()
    val recipesFilterByIngredient: LiveData<ArrayList<LocalRecipe>> = _recipesFilterByIngredient

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getRecipes() {
        viewModelScope.launch {
            viewModelScope.launch(coroutineContext) {
                _isLoading.postValue(true)
                _recipesLoaded.postValue(getRecipesUseCase())
                _isLoading.postValue(false)
            }
        }
    }

    fun filterByIngredient(recipesList: ArrayList<LocalRecipe>, queryParam: String?) {
        val filterRecipeList = ArrayList<LocalRecipe>()
        for (item in recipesList) {
            val itemUppercase = item.ingredients.map { it.uppercase() }
            if (queryParam?.uppercase() == item.name.uppercase()) {  //filter by name
                filterRecipeList.add(item)
            } else {                                                //filter by ingredient
                val ingredientsList = itemUppercase.filter { s -> s == queryParam?.uppercase() }
                if (ingredientsList.isNotEmpty())
                    filterRecipeList.add(item)
            }
        }
        _recipesFilterByIngredient.value = filterRecipeList
    }

    fun filterByLetterIngredient(recipesList: ArrayList<LocalRecipe>, queryParam: String?) {
        val filterRecipeList = ArrayList<LocalRecipe>()
        for (item in recipesList) {

            val itemUppercase = item.ingredients.map { it.uppercase() }

            queryParam?.let {
                if (item.name.uppercase().startsWith(queryParam.uppercase())) { //filter by name
                    filterRecipeList.add(item)
                } else {                                                        //filter by ingredient
                    queryParam.let {
                        val ingredientsList = itemUppercase.filter { s -> s.startsWith(queryParam.uppercase()) }
                        if (ingredientsList.isNotEmpty())
                            filterRecipeList.add(item)
                    }
                }
            }
            _recipesFilterByIngredient.value = filterRecipeList
        }
    }
}
