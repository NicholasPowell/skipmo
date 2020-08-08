package com.nilo.skipmo.model.domain.game

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.stream.Collectors


data class Deck(
        val cards: MutableList<Card> = mutableListOf(),
        val discards: MutableList<Card> = mutableListOf()
) {

    companion object {
        @JsonIgnore
        private val rules = DeckRules()
    }

    fun populated(): Deck {
        addSkipMos()
        addNumberedCards()
        return this
    }

    fun shuffled(): Deck {
        this.shuffle()
        return this
    }

    private fun addSkipMos() = repeat(8) { addToTop(Card(0)) }
    private fun addNumberedCards() = repeat(12) { (1..12).forEach { n -> addToTop(Card(n)) } }

    fun drawTopCard(): Card {
        rules.ifDeckIsEmptyReshuffleAndAddAllOfTheDiscardedCards(cards, discards, ::shuffle)
        return cards.removeAt(0)
    }

    fun addToTop(card: Card) = cards.add(0, card)
    fun shuffle() = cards.shuffle()

    fun size() = cards.size

    override fun toString() = /*        return "publicDeck[cardsLeft=${getSize()}]"*/ debugString()

    fun debugString() = "visibleCards=[${cards.stream().map(Card::toString).collect(Collectors.joining(","))}]"

}


