package com.aptivist.pokedex.domain

import com.aptivist.pokedex.domain.pokemon.Pokemon

interface IPokemonDataSource {

    suspend fun getPokemonByNameOrID(searchTerm: String) : Pokemon?

}