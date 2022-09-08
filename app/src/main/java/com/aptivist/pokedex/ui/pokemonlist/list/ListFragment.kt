package com.aptivist.pokedex.ui.pokemonlist.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aptivist.pokedex.databinding.FragmentListBinding
import com.aptivist.pokedex.domain.pokemon.PokemonListItem
import com.aptivist.pokedex.ui.pokemonlist.PokemonListViewModel
import com.aptivist.pokedex.ui.pokemonlist.detail.StatsAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val viewModel: PokemonListViewModel by activityViewModels()

    private val pokemonListAdapter by lazy { PokemonListAdapter(::onPokemonSelected) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        lifecycleScope.launchWhenStarted {
            viewModel.gotPokemon.collect {
                when(it){
                    ListUIEvents.SearchNavigationEvent ->  findNavController().navigate(
                        ListFragmentDirections.actionListFragmentToDetailFragment()
                    )
                    is ListUIEvents.ShowErrorEvent -> {
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                    }
                    is ListUIEvents.UpdatePokemonList -> println() //pokemonListAdapter.submitList(it.pokemonList)
                }
            }


        }


        return binding.root
    }

    private fun onPokemonSelected(pokemon : PokemonListItem) {
        viewModel.updateSearchText(pokemon.id.toString())
        viewModel.getPokemon()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etListSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.updateSearchText(text)
        }

        binding.btnListSearch.setOnClickListener {
            viewModel.getPokemon()
        }

        binding.recyclerView.adapter = pokemonListAdapter

        viewModel.getPokemonList()

        setObservers()

    }

    private fun setObservers() {

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.pokemonList.collectLatest {
                pokemonListAdapter.submitData(it)
            }
        }
    }
}