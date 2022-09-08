package com.aptivist.pokedex.data.api.pokemon.implementations

import androidx.paging.PagingData
import com.aptivist.pokedex.domain.IPokemonDataSource
import com.aptivist.pokedex.domain.pokemon.Pokemon
import com.aptivist.pokedex.domain.pokemon.PokemonImage
import com.aptivist.pokedex.domain.pokemon.PokemonListItem
import com.aptivist.pokedex.domain.pokemon.PokemonStats
import kotlinx.coroutines.flow.Flow

class MockDataSource : IPokemonDataSource {
    override suspend fun getPokemonByNameOrID(searchTerm: String): Pokemon {
        return Pokemon(1,12,22,"PokeChuy", PokemonImage(listOf(), ""), listOf(PokemonStats(1, "")), listOf(), listOf() )
    }

    override suspend fun getPokemonList(limit: Int): Flow<PagingData<PokemonListItem>> {
        TODO("Not yet implemented")
    }
}