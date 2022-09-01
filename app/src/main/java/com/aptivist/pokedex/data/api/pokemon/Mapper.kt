package com.aptivist.pokedex.data.api.pokemon

import com.aptivist.pokedex.data.api.pokemon.models.PokeDao
import com.aptivist.pokedex.data.api.pokemon.models.StatsDao
import com.aptivist.pokedex.data.api.pokemon.models.pokemonlist.PokemonResultListDAO
import com.aptivist.pokedex.data.api.pokemon.models.pokemonlist.ResultDAO
import com.aptivist.pokedex.domain.pokemon.Pokemon
import com.aptivist.pokedex.domain.pokemon.PokemonImage
import com.aptivist.pokedex.domain.pokemon.PokemonStats

fun PokeDao.toDomainPokemon() : Pokemon {
    val images = PokemonImage(listOf(this.sprites.front_default, this.sprites.back_default, this.sprites.front_female, this.sprites.back_female, this.sprites.front_shiny, this.sprites.back_shiny, this.sprites.front_shiny_female, this.sprites.back_shiny_female), this.sprites.other.official_artwork.front_default)
    val stats = this.stats.map {
        it.toDomainPokemonStats()
    }
    val types = this.types.map { it.type.name }
    val moves = this.moves.map { it.move.name }
    return Pokemon(this.id, this.height, this.weight, this.name, images, stats, types, moves)
}

fun StatsDao.toDomainPokemonStats() : PokemonStats {
    return PokemonStats(this.base_stat, this.stat.name)
}

fun PokemonResultListDAO.toDomainPokemonList()  {
   /* val images = PokemonImage(listOf(this.sprites.front_default, this.sprites.back_default, this.sprites.front_female, this.sprites.back_female, this.sprites.front_shiny, this.sprites.back_shiny, this.sprites.front_shiny_female, this.sprites.back_shiny_female), this.sprites.other.official_artwork.front_default)
    val stats = this.stats.map {
        it.toDomainPokemonStats()
    }
    val types = this.types.map { it.type.name }
    val moves = this.moves.map { it.move.name }
    return Pokemon(this.id, this.height, this.weight, this.name, images, stats, types, moves)*/
}

fun ResultDAO.toDomainPokemonResult()  {
   // return PokemonStats(this.base_stat, this.stat.name)
}