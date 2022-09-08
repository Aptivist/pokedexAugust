package com.aptivist.pokedex.domain

import androidx.paging.PagingData
import com.aptivist.pokedex.domain.pokemon.Pokemon
import com.aptivist.pokedex.domain.pokemon.PokemonListItem
import kotlinx.coroutines.flow.Flow

interface IPokemonDataSource {

    suspend fun getPokemonByNameOrID(searchTerm: String) : Pokemon

    suspend fun getPokemonList(limit : Int): Flow<PagingData<PokemonListItem>>
}
