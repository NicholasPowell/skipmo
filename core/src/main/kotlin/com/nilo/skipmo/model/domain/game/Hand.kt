package com.nilo.skipmo.model.domain.game

import com.fasterxml.jackson.annotation.JsonIgnore
import com.nilo.skipmo.api.domain.HandSlotIndex

data class Hand(
        var cards: ArrayList<Card?> = arrayListOf(
                *arrayOfNulls(HandSlotIndex.values().size)
        )
) {

    companion object {
        @JsonIgnore
        val rules: HandRules = HandRules()
    }

    fun peek(slot: HandSlotIndex): Card? {
        return cards[slot.index]
    }

    fun play(slot: HandSlotIndex): Card {
        val card = cards[slot.index]
        rules.youCannotPlayFromAnEmptyHandSlot(slot, this)
        cards[slot.index] = null
        return card!!
    }

    fun swap(slot1: HandSlotIndex, slot2: HandSlotIndex) {
        val temp = cards[slot1.index]
        cards[slot1.index] = cards[slot2.index]
        cards[slot2.index] = temp
    }

    fun drawToFull(deck: Deck) {
        HandSlotIndex.values().forEach {
            if (cards[it.index] == null) takeCard(it, deck.drawTopCard())
        }
    }

    fun takeCard(slot: HandSlotIndex, card: Card) {
        if (cards[slot.index] == null) {
            cards[slot.index] = card
        } else {
            throw RuntimeException("tried adding a card to an already full slot")
        }
    }

    fun takeCard(card: Card) {
        val firstIndex = cards.indexOfFirst { it == null }
        if (firstIndex > -1)
            cards[firstIndex] = card
        else
            throw Exception("tried to draw a card, but visibleHand was full")
    }

    fun size() = cards.filterNotNull().size
    override fun toString() = cards.joinToString(",")

    fun validateBeforePlay(slot: HandSlotIndex, fx: (Card) -> Unit) {
        rules.youCannotPlayFromAnEmptyHandSlot(slot, this)
        val card = cards[slot.index]!!
        fx(card)
    }

}