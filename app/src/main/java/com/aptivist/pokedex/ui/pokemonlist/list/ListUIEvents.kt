package com.aptivist.pokedex.ui.pokemonlist.list

import com.aptivist.pokedex.domain.pokemon.PokemonListItem

sealed class ListUIEvents{
    object SearchNavigationEvent : ListUIEvents()
    data class ShowErrorEvent(val message:String) : ListUIEvents()
    data class UpdatePokemonList(val pokemonList: List<PokemonListItem>):ListUIEvents()
}

