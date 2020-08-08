package com.nilo.skipmo.model.domain.game

class DiscardPileRules {
    fun youCannotPlayFromAnEmptyDiscardPile(cards: List<Card>) {
        if (cards.isEmpty()) {
            throw Exception("You cannot play from an empty discard pile")
        }
    }
}