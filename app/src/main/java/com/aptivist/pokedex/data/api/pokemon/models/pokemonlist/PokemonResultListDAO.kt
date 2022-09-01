package com.aptivist.pokedex.data.api.pokemon.models.pokemonlist

data class PokemonResultListDAO(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<ResultDAO>
)