package com.aptivist.pokedex.ui.pokemonlist.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aptivist.pokedex.databinding.StatsItemBinding
import com.aptivist.pokedex.domain.pokemon.PokemonStats

class StatsAdapter : ListAdapter<PokemonStats, StatsAdapter.StatsViewHolder>(DiffCallbackPokemonStats()) {

    inner class StatsViewHolder(private val itemBinding: StatsItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(stats : PokemonStats) {
            with(itemBinding){
                tvStatsItemTitle.text = stats.name
                tvStatsItemValue.text = stats.value.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        return StatsViewHolder(StatsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallbackPokemonStats : DiffUtil.ItemCallback<PokemonStats>() {

    override fun areItemsTheSame(oldItem: PokemonStats, newItem: PokemonStats): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PokemonStats, newItem: PokemonStats): Boolean {
        return oldItem.name == newItem.name && oldItem.value == oldItem.value
    }

}