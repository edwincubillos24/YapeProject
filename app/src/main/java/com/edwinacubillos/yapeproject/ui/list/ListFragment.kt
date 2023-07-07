package com.edwinacubillos.yapeproject.ui.list

import BaseBottomSheetDialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.yapeproject.domain.remote.data.LocalRecipe
import com.edwinacubillos.yapeproject.R
import com.edwinacubillos.yapeproject.databinding.FragmentListBinding
import com.edwinacubillos.yapeproject.utils.gone
import com.edwinacubillos.yapeproject.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private val listViewModel: ListViewModel by viewModel()
    private lateinit var listBinding : FragmentListBinding
    private var recipesList = ArrayList<LocalRecipe>()
    private lateinit var recipesRecyclerViewAdapter: RecipesRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = listBinding.root

        val recipesList = ArrayList<LocalRecipe>()
        recipesRecyclerViewAdapter = RecipesRecyclerViewAdapter(recipesList, onItemClicked = { onRecipeItemClicked(it) })

        listBinding.recipesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = recipesRecyclerViewAdapter
            setHasFixedSize(false)
        }

        listViewModel.recipesLoaded.observe(viewLifecycleOwner) { result ->
            result?.let { resourceRemote ->
                when (result) {
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.let { wrappedResponse ->
                            wrappedResponse.data?.let { recipesList ->
                                this.recipesList = recipesList as ArrayList<LocalRecipe>
                                recipesRecyclerViewAdapter.appendItems(this.recipesList)
                            }
                        }
                    }

                    is ResourceRemote.Error -> {
                        resourceRemote.message?.let { showMessageError(it) }
                    }

                    else -> {
                        //don't use
                    }
                }
            }
        }

        listViewModel.isLoading.observe(viewLifecycleOwner) { result ->
            result?.let { isLoading ->
                if (isLoading) listBinding.loadingLayout.loaderModalContainer.show()
                else listBinding.loadingLayout.loaderModalContainer.gone()
            }
        }

        listViewModel.recipesFilterByIngredient.observe(viewLifecycleOwner){
            recipesRecyclerViewAdapter.appendItems(it)
        }

        listViewModel.getRecipes()

        listBinding.searchView.setOnQueryTextListener(this)

        return root
    }

    private fun showMessageError(message: String) {
        BaseBottomSheetDialogFragment.show(
            fragmentManager = childFragmentManager,
            customTitle = getString(R.string.warning),
            customDescription = message,
            customConfirmButtonTitle = getString(R.string.continue_label),
            customCancelButtonTitle = null
        )
    }

    private fun onRecipeItemClicked(localRecipe: LocalRecipe) {
        listBinding.searchView.setQuery("",false)
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(localRecipe))
    }

    override fun onQueryTextSubmit(queryParam: String?): Boolean {
        listViewModel.filterByIngredient(recipesList, queryParam?.trim())
        return false
    }

    override fun onQueryTextChange(queryParam: String?): Boolean {
        listViewModel.filterByLetterIngredient(recipesList, queryParam?.trim())
        return false
    }
}
