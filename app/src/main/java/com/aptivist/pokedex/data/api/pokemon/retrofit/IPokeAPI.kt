package com.aptivist.pokedex.data.api.pokemon.retrofit

import com.aptivist.pokedex.data.api.pokemon.models.PokeDao
import com.aptivist.pokedex.data.api.pokemon.models.pokemonlist.PokemonResultListDAO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IPokeAPI {

    @GET("pokemon/{searchTerm}")
    suspend fun getPokemonByNameOrID(@Path("searchTerm") searchTerm : String) : Response<PokeDao>

    @GET("pokemon")
    suspend fun getPokemonList(@Query("offset") offset : Int,@Query("limit") limit : Int) : Response<PokemonResultListDAO>
}