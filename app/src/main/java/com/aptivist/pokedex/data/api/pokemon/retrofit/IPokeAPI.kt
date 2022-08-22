package com.aptivist.pokedex.data.api.pokemon.retrofit

import com.aptivist.pokedex.data.api.pokemon.models.PokeDao
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IPokeAPI {

    @GET("pokemon/{searchTerm}")
    suspend fun getPokemonByNameOrID(@Path("searchTerm") searchTerm : String) : Response<PokeDao>

}