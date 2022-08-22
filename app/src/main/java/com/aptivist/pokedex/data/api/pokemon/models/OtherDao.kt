package com.aptivist.pokedex.data.api.pokemon.models

import com.google.gson.annotations.SerializedName

data class OtherDao(
    @SerializedName("official-artwork") val official_artwork: OfficialArtworkDao
)