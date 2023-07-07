package com.edwinacubillos.yapeproject.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edwinacubillos.yapeproject.domain.remote.data.LocalRecipe
import com.edwinacubillos.yapeproject.R
import com.edwinacubillos.yapeproject.databinding.CardViewItemRecipesBinding
import com.squareup.picasso.Picasso

class RecipesRecyclerViewAdapter (
    private val recipesList: ArrayList<LocalRecipe>,
    private val onItemClicked: (LocalRecipe) -> Unit
) : RecyclerView.Adapter<RecipesRecyclerViewAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_recipes, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val localRecipe = recipesList[position]
        holder.bindCat(localRecipe)
        holder.itemView.setOnClickListener { onItemClicked(recipesList[position]) }
    }

    override fun getItemCount(): Int = recipesList.size

    fun appendItems(newList: ArrayList<LocalRecipe>){
        recipesList.clear()
        recipesList.addAll(newList)
        notifyDataSetChanged()
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardViewItemRecipesBinding.bind(itemView)

        fun bindCat(localRecipe: LocalRecipe) {
            with(binding){
                recipeNameTextView.text = localRecipe.name
                Picasso.get().load(localRecipe.urlPicture).into(pictureImageView)
            }
        }
    }
}
