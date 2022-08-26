package com.aptivist.pokedex.ui.pokemonlist.list

sealed class ListUIEvents{
    object SearchNavigationEvent : ListUIEvents()
    data class ShowErrorEvent(val message:String) : ListUIEvents()
}
