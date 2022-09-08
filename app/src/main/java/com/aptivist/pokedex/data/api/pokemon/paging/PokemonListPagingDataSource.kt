package com.aptivist.pokedex.data.api.pokemon.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aptivist.pokedex.data.api.pokemon.models.pokemonlist.PokemonListPageInfo
import com.aptivist.pokedex.data.api.pokemon.models.pokemonlist.ResultDAO
import com.aptivist.pokedex.data.api.pokemon.retrofit.IPokeAPI

@Suppress("BlockingMethodInNonBlockingContext")
class PokemonListPagingDataSource(private val pokeAPI: IPokeAPI) :
    PagingSource<PokemonListPageInfo, ResultDAO>() {

    override fun getRefreshKey(state: PagingState<PokemonListPageInfo, ResultDAO>): PokemonListPageInfo = PokemonListPageInfo()

    override suspend fun load(params: LoadParams<PokemonListPageInfo>): LoadResult<PokemonListPageInfo, ResultDAO> {
        return try {
            var previousPageSize = 0
            var previousOffset = 0
            val response = params.key?.let {
                previousPageSize = it.pageSize
                previousOffset = it.offset
                pokeAPI.getPokemonList(it.offset, it.pageSize)
            }

            response?.let {
                if (it.isSuccessful) {
                    val pagedResponse = it.body()
                    val pokemonList = pagedResponse?.results
                    val previousPageResponse = pagedResponse?.previous
                    val nextPageResponse = pagedResponse?.next
                    var prevKey: PokemonListPageInfo? = null
                    var nextKey: PokemonListPageInfo? = null

                    if (previousPageResponse != null) {
                        prevKey = PokemonListPageInfo()
                        val previousUri = Uri.parse(previousPageResponse)
                        prevKey.offset = previousUri.getQueryParameter(OFFSET_QUERY_KEY)?.toInt() ?: 0
                        prevKey.pageSize = previousUri.getQueryParameter(LIMIT_QUERY_KEY)?.toInt() ?: previousPageSize
                    }

                    if (nextPageResponse != null) {
                        nextKey = PokemonListPageInfo()
                        val nextUri = Uri.parse(nextPageResponse)
                        nextKey.offset = nextUri.getQueryParameter(OFFSET_QUERY_KEY)?.toInt() ?: previousOffset
                        nextKey.pageSize = nextUri.getQueryParameter(LIMIT_QUERY_KEY)?.toInt() ?: 0
                    }

                    LoadResult.Page(
                        pokemonList.orEmpty(),
                        prevKey,
                        nextKey
                    )
                }
                else
                {
                    LoadResult.Error(IllegalStateException(response.errorBody()?.string() ?: "Unknown API Exception"))
                }
            } ?: LoadResult.Error(IllegalArgumentException("Response was null or page data might be null"))

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}

const val OFFSET_QUERY_KEY = "offset"
const val LIMIT_QUERY_KEY = "limit"