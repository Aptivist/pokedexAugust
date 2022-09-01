package com.aptivist.pokedex.data.api.pokemon.implementations

import com.aptivist.pokedex.domain.IPokemonDataSource
import com.aptivist.pokedex.domain.pokemon.Pokemon
import com.aptivist.pokedex.domain.pokemon.PokemonImage
import com.aptivist.pokedex.domain.pokemon.PokemonListItem
import com.aptivist.pokedex.domain.pokemon.PokemonStats

class MockDataSource : IPokemonDataSource {
    override suspend fun getPokemonByNameOrID(searchTerm: String): Pokemon {
        return Pokemon(1,12,22,"PokeChuy", PokemonImage(listOf(), ""), listOf(PokemonStats(1, "")), listOf(), listOf() )
    }

    override suspend fun getPokemonList(offset: Int, limit: Int): List<PokemonListItem> {
        TODO("Not yet implemented")
    }
}