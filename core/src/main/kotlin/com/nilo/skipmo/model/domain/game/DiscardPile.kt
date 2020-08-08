package com.nilo.skipmo.model.domain.game

import com.fasterxml.jackson.annotation.JsonIgnore

data class DiscardPile(
        val cards: MutableList<Card> = mutableListOf()
) {

    companion object {
        @JsonIgnore
        val rules: DiscardPileRules = DiscardPileRules()
    }

    fun showTopCard() = cards[0]
    fun addToTop(card: Card) = cards.add(0, card)
    fun play() = cards.removeAt(0)
    fun validateBeforePlay() = rules.youCannotPlayFromAnEmptyDiscardPile(cards)
    fun getSize() = cards.size

    override fun toString(): String {
        return if (getSize() > 0) cards[0].toString() + "(" + getSize() + ")" else "()"
    }

}