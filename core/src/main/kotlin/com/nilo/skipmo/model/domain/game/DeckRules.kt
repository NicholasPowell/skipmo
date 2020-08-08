package com.nilo.skipmo.model.domain.game

class DeckRules {

    fun ifDeckIsEmptyReshuffleAndAddAllOfTheDiscardedCards(cards: MutableList<Card>, discards: MutableList<Card>, shuffle: () -> Unit) {
        if (cards.size == 0) {
            cards.addAll(discards)
            discards.clear()
            shuffle()
        }
    }
}