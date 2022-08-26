package com.aptivist.pokedex.ui.pokemonlist.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aptivist.pokedex.databinding.PokemonIconItemBinding
import com.aptivist.pokedex.domain.IImageLoader

class ImageAdapter(private val imageLoader: IImageLoader) : ListAdapter<String, ImageAdapter.ImageViewHolder>(DiffCallbackImage()) {

    inner class ImageViewHolder(private val itemBinding: PokemonIconItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(value : String) {
            with(itemBinding){
                imageLoader.loadImage(value, ivDetailsIcon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(PokemonIconItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallbackImage : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}