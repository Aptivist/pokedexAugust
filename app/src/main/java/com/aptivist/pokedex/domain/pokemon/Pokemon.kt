package com.aptivist.pokedex.domain.pokemon

data class Pokemon (
    val id: Int,
    val height: Int,
    val weight: Int,
    val name: String,
    val sprites: PokemonImage,
    val stats: List<PokemonStats>,
    val types: List<String>,
    val moves: List<String>,
)