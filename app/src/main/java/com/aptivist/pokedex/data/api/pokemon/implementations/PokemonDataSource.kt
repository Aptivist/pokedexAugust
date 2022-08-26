package com.aptivist.pokedex.data.api.pokemon.implementations

import android.content.res.Resources
import com.aptivist.pokedex.data.api.pokemon.retrofit.IPokeAPI
import com.aptivist.pokedex.data.api.pokemon.toDomainPokemon
import com.aptivist.pokedex.domain.IPokemonDataSource
import com.aptivist.pokedex.domain.pokemon.Pokemon
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

}