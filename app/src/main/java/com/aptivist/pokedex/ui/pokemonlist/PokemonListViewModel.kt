package com.aptivist.pokedex.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aptivist.pokedex.domain.IPokemonDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor (private var dataSource: IPokemonDataSource) : ViewModel() {

    var searchText = ""

    fun getPokemon() {
        if (searchText.isNotEmpty()){
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val pokemon = dataSource.getPokemonByNameOrID(searchText)
                    println(pokemon)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }
    }

    fun updateSearchText(text: CharSequence?) {
        searchText = text.toString()
    }
}