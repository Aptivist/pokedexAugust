package com.aptivist.pokedex.ui.pokemonlist.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.aptivist.pokedex.R
import com.aptivist.pokedex.databinding.FragmentDetailBinding
import com.aptivist.pokedex.domain.IImageLoader
import com.aptivist.pokedex.ui.pokemonlist.PokemonListViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: PokemonListViewModel by activityViewModels()

    @Inject lateinit var imageLoader : IImageLoader

    private val statsAdapter by lazy { StatsAdapter() }
    private val typesAdapter by lazy { TextAdapter() }
    private val movesAdapter by lazy { TextAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvDetailsID.text = viewModel.currentPokemon?.id.toString()
            tvDetailsHeight.text =
                String.format(getString(R.string.height_label), viewModel.currentPokemon?.height)
            tvDetailsWeight.text =
                String.format(getString(R.string.weight_label), viewModel.currentPokemon?.weight)

            viewModel.currentPokemon?.sprites?.display?.let {
                imageLoader.loadImage(
                    it,
                    ivDetailsMain
                )
            }

            rvDetailStats.adapter = statsAdapter
            rvDetailMoves.adapter = movesAdapter
            rvDetailTypes.adapter = typesAdapter

            statsAdapter.submitList(viewModel.currentPokemon?.stats)
            movesAdapter.submitList(viewModel.currentPokemon?.moves)
            typesAdapter.submitList(viewModel.currentPokemon?.types)
        }



    }

}
