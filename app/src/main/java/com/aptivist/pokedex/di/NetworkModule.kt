package com.aptivist.pokedex.di

import com.aptivist.pokedex.data.api.pokemon.implementations.PokemonDataSource
import com.aptivist.pokedex.data.api.pokemon.retrofit.IPokeAPI
import com.aptivist.pokedex.domain.IPokemonDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePokemonAPI(retrofit: Retrofit) : IPokeAPI {
        return retrofit.create(IPokeAPI::class.java)
    }

    @Singleton
    @Provides
    fun providePokemonDataSource(pokeAPI: IPokeAPI) : IPokemonDataSource = PokemonDataSource(pokeAPI)

}