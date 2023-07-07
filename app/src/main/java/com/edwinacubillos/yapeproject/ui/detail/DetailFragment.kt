package com.edwinacubillos.yapeproject.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.edwinacubillos.yapeproject.R
import com.edwinacubillos.yapeproject.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = detailBinding.root

        val recipe = args.localRecipe

        var ingredients = "\n"
        for (item in recipe.ingredients)
            ingredients = ingredients + "\n" + item

        with(detailBinding){
            nameTextView.text = recipe.name
            ingredientsTextView.text = getString(R.string.ingredients, ingredients)
            instructionsTextView.text = getString(R.string.instructions, recipe.instructions)
            originCountryTextView.text = getString(R.string.origen_country,recipe.originCountry)
            Picasso.get().load(recipe.urlPicture).into(pictureImageView)

            mapIconImageView.setOnClickListener {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToMapsFragment(recipe))
            }
        }

        return root
    }
}
