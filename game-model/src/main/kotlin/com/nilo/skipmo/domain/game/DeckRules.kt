package com.nilo.skipmo.domain.game

internal class DeckRules(
        val cards: MutableList<Card>,
        val discards: MutableList<Card> = mutableListOf(),
        val shuffle: () -> Unit
        ) {

    fun ifDeckIsEmptyReshuffleAndAddAllOfTheDiscardedCards() {
        if( cards.size == 0 ) {
            cards.addAll(discards)
            discards.clear()
            shuffle()
        }
    }
}