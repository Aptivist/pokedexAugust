package com.aptivist.pokedex.domain

import com.aptivist.pokedex.domain.pokemon.Pokemon
import com.aptivist.pokedex.domain.pokemon.PokemonListItem

interface IPokemonDataSource {

    suspend fun getPokemonByNameOrID(searchTerm: String) : Pokemon

    suspend fun getPokemonList(offset : Int, limit : Int): List<PokemonListItem>
}
