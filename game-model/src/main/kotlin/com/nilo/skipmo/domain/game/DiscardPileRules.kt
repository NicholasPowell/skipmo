package com.nilo.skipmo.domain.game

internal class DiscardPileRules(
        val cards: MutableList<Card>,
        private val handleException : (String) -> Unit
){
    fun youCannotPlayFromAnEmptyDiscardPile() {
        if( cards.size == 0 ) {
            handleException("You cannot play from an empty discard pile")
        }
    }
}