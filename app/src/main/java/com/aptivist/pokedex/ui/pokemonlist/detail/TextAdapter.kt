package com.aptivist.pokedex.ui.pokemonlist.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aptivist.pokedex.databinding.TextListItemBinding

class TextAdapter : ListAdapter<String, TextAdapter.TextViewHolder>(DiffCallbackText()) {

    inner class TextViewHolder(private val itemBinding: TextListItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(value : String) {
            with(itemBinding){
                tvTextItemValue.text = value
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        return TextViewHolder(TextListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallbackText : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}