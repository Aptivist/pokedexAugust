package com.aptivist.pokedex.ui.pokemonlist.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.aptivist.pokedex.databinding.FragmentListBinding
import com.aptivist.pokedex.ui.pokemonlist.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val viewModel: PokemonListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        viewModel.gotPokemon.observe(viewLifecycleOwner) {
            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailFragment()
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etListSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.updateSearchText(text)
        }

        binding.btnListSearch.setOnClickListener {
            viewModel.getPokemon()
        }

    }
}