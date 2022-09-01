package com.aptivist.pokedex.data.api.pokemon.implementations

import android.content.res.Resources
import com.aptivist.pokedex.data.api.pokemon.retrofit.IPokeAPI
import com.aptivist.pokedex.data.api.pokemon.toDomainPokemon
import com.aptivist.pokedex.data.api.pokemon.toDomainPokemonList
import com.aptivist.pokedex.domain.IPokemonDataSource
import com.aptivist.pokedex.domain.pokemon.Pokemon
import com.aptivist.pokedex.domain.pokemon.PokemonListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
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

    override suspend fun getPokemonList(offset: Int, limit : Int) : List<PokemonListItem> {
        val response = pokeAPI.getPokemonList(offset,limit)
        var listPokemon : List<PokemonListItem>? = null
        if(response.isSuccessful){
            listPokemon = response.body()?.results?.map { it.toDomainPokemonList() }
        }
        return listPokemon ?: throw Resources.NotFoundException("No pokemon found")
    }

}