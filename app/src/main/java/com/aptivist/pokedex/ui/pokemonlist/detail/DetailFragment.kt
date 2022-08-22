package com.aptivist.pokedex.ui.pokemonlist.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.aptivist.pokedex.R
import com.aptivist.pokedex.databinding.FragmentDetailBinding
import com.aptivist.pokedex.ui.pokemonlist.PokemonListViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: PokemonListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

}