package com.aptivist.pokedex.ui.pokemonlist.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aptivist.pokedex.databinding.TextListItemBinding
import com.aptivist.pokedex.domain.pokemon.PokemonListItem

class PokemonListAdapter(private val onPokemonSelected: (PokemonListItem) -> Unit) : PagingDataAdapter<PokemonListItem, PokemonListAdapter.TextViewHolder>(DiffCallPokemonItem()) {

    inner class TextViewHolder(private val itemBinding: TextListItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(value : PokemonListItem) {
            with(itemBinding){
                tvTextItemValue.text = value.name
                cvTextItemElement.setOnClickListener {
                    onPokemonSelected(value)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        return TextViewHolder(TextListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class DiffCallPokemonItem : DiffUtil.ItemCallback<PokemonListItem>() {

    override fun areItemsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
        return oldItem == newItem
    }

}