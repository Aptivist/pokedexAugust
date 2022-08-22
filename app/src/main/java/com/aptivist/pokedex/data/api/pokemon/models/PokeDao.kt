package com.aptivist.pokedex.data.api.pokemon.models

data class PokeDao(
    val height: Int,
    val id: Int,
    val moves: List<MovesDao>,
    val name: String,
    val sprites: SpritesDao,
    val stats: List<StatsDao>,
    val types: List<TypesDao>,
    val weight: Int
)