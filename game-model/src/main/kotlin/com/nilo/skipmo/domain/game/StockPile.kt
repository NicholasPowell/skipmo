package com.nilo.skipmo.domain.game

import com.nilo.skipmo.domain.public.StockPile

internal class StockPile(
        val cards : MutableList<Card> = mutableListOf(),
        private val rules : StockPileRules = StockPileRules(cards) { message: String -> throw Exception(message) }
) {

    fun play(buildPile: BuildPile, win: ()->Unit) {
        rules.youCannotPlayFromAnEmptyStockPile()
        buildPile.play(cards[0]) { drawTopCard() }
        if( rules.playerWinsIfLastCardPlayedFromStockPile() ) win() //!!!
    }

    fun addToTop( card : Card) =cards.add(0,card)
    private fun size() = cards.size
    private fun drawTopCard( ) = cards.removeAt(0)
    override fun toString() = if(cards.size > 0) cards[0].toString() + "(" + size() + ")" else "()"

    fun toDto() = StockPile(cards.map { it.CardDtoConverter().convert() })
}
