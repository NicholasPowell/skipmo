package com.nilo.skipmo.model.domain.game

import com.fasterxml.jackson.annotation.JsonIgnore

internal data class StockPile(val cards: MutableList<Card> = mutableListOf()) {

    companion object {
        @JsonIgnore
        private val rules: StockPileRules = StockPileRules()
    }

    fun play(buildPile: BuildPile, win: () -> Unit) {
        rules.youCannotPlayFromAnEmptyStockPile(cards)
        buildPile.play(cards[0]) { drawTopCard() }
        if (rules.playerWinsIfLastCardPlayedFromStockPile(cards)) win() //!!!
    }

    fun addToTop(card: Card) = cards.add(0, card)
    private fun size() = cards.size
    private fun drawTopCard() = cards.removeAt(0)
    override fun toString() = if (cards.size > 0) cards[0].toString() + "(" + size() + ")" else "()"

}
