package com.nilo.skipmo.domain.game

internal class StockPileRules(private val cards : MutableList<Card>, private val handleException: (String)->Unit ) {

    fun youCannotPlayFromAnEmptyStockPile() {
        if( cards.size == 0 ) {
            handleException("You cannot play from an empty stock pile")
        }
    }
    fun playerWinsIfLastCardPlayedFromStockPile() : Boolean {
        return cards.size == 0
    }

}