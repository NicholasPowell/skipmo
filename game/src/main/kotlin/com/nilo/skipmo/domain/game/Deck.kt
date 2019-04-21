package com.nilo.skipmo.domain.game

import com.nilo.skipmo.domain.public.Deck
import java.util.stream.Collectors


internal class Deck(
        private val cards : MutableList<Card> = mutableListOf(),
        val discards : MutableList<Card> = mutableListOf()
) {
    private val rules = DeckRules(cards, discards, ::shuffle)

    fun drawTopCard() : Card {
        rules.ifDeckIsEmptyReshuffleAndAddAllOfTheDiscardedCards()
        return cards.removeAt(0)
    }

    fun addToTop( card : Card) = cards.add(0,card)
    fun shuffle() = cards.shuffle()

    fun size() = cards.size

    override fun toString() = /*        return "deck[cardsLeft=${getSize()}]"*/ debugString()

    fun debugString() = "cards=[${cards.stream().map(Card::toString).collect(Collectors.joining(","))}]"

    fun toDto() = Deck(cards.map { it.CardDtoConverter().convert() }, discards.map { it.CardDtoConverter().convert() })

}


