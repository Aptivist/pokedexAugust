package com.aptivist.pokedex.data.api.pokemon.models

data class StatsDao(
    val base_stat: Int,
    val effort: Int,
    val stat: StatDao
)