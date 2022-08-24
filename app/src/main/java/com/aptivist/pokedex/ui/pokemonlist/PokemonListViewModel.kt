package com.aptivist.pokedex.ui.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aptivist.pokedex.domain.IPokemonDataSource
import com.aptivist.pokedex.domain.pokemon.Pokemon
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor (private var dataSource: IPokemonDataSource) : ViewModel() {

    private val _gotPokemon = LiveEvent<Any>()
    val gotPokemon : LiveData<Any>
        get() = _gotPokemon

    private var _currentPokemon : Pokemon? = null
    val currentPokemon : Pokemon?
        get() = _currentPokemon

    var searchText = ""

    fun getPokemon() {
        if (searchText.isNotEmpty()){
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    _currentPokemon = dataSource.getPokemonByNameOrID(searchText)
                    println(_currentPokemon)
                    _gotPokemon.postValue(true)
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