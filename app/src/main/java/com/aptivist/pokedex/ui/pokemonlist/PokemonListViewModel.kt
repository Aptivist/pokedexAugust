package com.aptivist.pokedex.ui.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aptivist.pokedex.domain.IPokemonDataSource
import com.aptivist.pokedex.domain.pokemon.Pokemon
import com.aptivist.pokedex.domain.pokemon.PokemonListItem
import com.aptivist.pokedex.ui.pokemonlist.list.ListUIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor (private var dataSource: IPokemonDataSource) : ViewModel() {

    private val _gotPokemon = Channel<ListUIEvents>()
    val gotPokemon = _gotPokemon.receiveAsFlow()

    private var _currentPokemon : Pokemon? = null
    val currentPokemon : Pokemon?
        get() = _currentPokemon

    private val _pokemonList = MutableLiveData<List<PokemonListItem>>()
    val pokemonList : LiveData<List<PokemonListItem>>
        get() = _pokemonList

    var searchText = ""

    fun getPokemon() {
        if (searchText.isNotEmpty()){
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    _currentPokemon = dataSource.getPokemonByNameOrID(searchText)
                    _gotPokemon.trySend(ListUIEvents.SearchNavigationEvent)
                } catch (e: Exception) {
                    _gotPokemon.trySend(ListUIEvents.ShowErrorEvent(e.message ?: "Unknown"))
                }
            }
        }
    }

    fun updateSearchText(text: CharSequence?) {
        searchText = text.toString()
    }

    fun getPokemonList(){
        viewModelScope.launch(Dispatchers.IO){
            try {

                val result = dataSource.getPokemonList(0,20)

                launch(Dispatchers.Main) {
                    _pokemonList.value = result
                }

            } catch (e: Exception) {
                _gotPokemon.trySend(ListUIEvents.ShowErrorEvent(e.message ?: "Unknown"))
            }
        }
    }


}