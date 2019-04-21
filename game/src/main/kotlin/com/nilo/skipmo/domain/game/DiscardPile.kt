package com.nilo.skipmo.domain.game

import com.nilo.skipmo.domain.public.DiscardPile

internal class DiscardPile(
        val cards : MutableList<Card> = mutableListOf(),
        val rules : DiscardPileRules = DiscardPileRules(cards) { message: String -> throw Exception(message) }
) {

    enum class Index(val index : Int)  { ONE(0), TWO(1), THREE(2), FOUR(3) }

    fun showTopCard() = cards[0]
    fun addToTop( card : Card) = cards.add(0,card)
    fun play() = cards.removeAt(0)
    fun validateBeforePlay() = rules.youCannotPlayFromAnEmptyDiscardPile()
    fun getSize() = cards.size

    override fun toString(): String {
        return if( getSize() > 0 ) cards[0].toString() + "(" + getSize() + ")" else "()"
    }

    fun toDto() = DiscardPile(cards.map { it.CardDtoConverter().convert() })

}