package com.aptivist.pokedex.data.api.pokemon.implementations

import android.content.res.Resources
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.aptivist.pokedex.data.api.pokemon.models.pokemonlist.PokemonListPageInfo
import com.aptivist.pokedex.data.api.pokemon.paging.PokemonListPagingDataSource
import com.aptivist.pokedex.data.api.pokemon.retrofit.IPokeAPI
import com.aptivist.pokedex.data.api.pokemon.toDomainPokemon
import com.aptivist.pokedex.data.api.pokemon.toDomainPokemonList
import com.aptivist.pokedex.domain.IPokemonDataSource
import com.aptivist.pokedex.domain.pokemon.Pokemon
import com.aptivist.pokedex.domain.pokemon.PokemonListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonDataSource @Inject constructor(private val pokeAPI: IPokeAPI) : IPokemonDataSource {

    override suspend fun getPokemonByNameOrID(searchTerm: String): Pokemon {
        val response = pokeAPI.getPokemonByNameOrID(searchTerm)
        var pokemon : Pokemon? = null
        if (response.isSuccessful){
            pokemon = response.body()?.toDomainPokemon()
        }
        return pokemon ?: throw Resources.NotFoundException("No pokemon found")
    }

    override suspend fun getPokemonList(limit: Int): Flow<PagingData<PokemonListItem>>  = Pager(
        config = PagingConfig(prefetchDistance = 2, pageSize = limit),
        initialKey = PokemonListPageInfo(pageSize = limit),
        pagingSourceFactory = { PokemonListPagingDataSource(pokeAPI) }
    ).flow.map { pagingData -> pagingData.map { result -> result.toDomainPokemonList() }}

}