package com.nilo.skipmo.model.domain.game

class StockPileRules {

    fun youCannotPlayFromAnEmptyStockPile(cards: List<Card>) {
        if (cards.isEmpty()) {
            throw Exception("You cannot play from an empty stock pile")
        }
    }

    fun playerWinsIfLastCardPlayedFromStockPile(cards: List<Card>): Boolean {
        return cards.isEmpty()
    }

}