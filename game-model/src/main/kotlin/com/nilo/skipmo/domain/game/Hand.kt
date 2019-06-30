package com.nilo.skipmo.domain.game

import com.nilo.skipmo.domain.public.Hand

internal class Hand(
        var cards : Array<Card?> = arrayOfNulls(Slot.values().size),
        val rules : HandRules = HandRules(cards) { message: String -> throw Exception(message) }
){

    enum class Slot(val index : Int)  { ONE(0), TWO(1), THREE(2), FOUR(3), FIVE(4) }

    fun peek( slot : Slot) : Card? {
        return cards[slot.index]
    }

    fun play( slot : Slot) : Card {
        val card = cards[slot.index]
        rules.youCannotPlayFromAnEmptyHandSlot(slot)
        cards[slot.index] = null
        return card!!
    }

    fun swap(slot1: Slot, slot2: Slot) {
        val temp = cards[slot1.index]
        cards[slot1.index] = cards[slot2.index]
        cards[slot2.index] = temp
    }

    fun drawToFull( deck: Deck) {
        Slot.values().forEach {
            if( cards[it.index] == null ) takeCard(it, deck.drawTopCard())
        }
    }

    fun takeCard(slot : Slot, card : Card) {
        if( cards[slot.index] == null ) {
            cards[slot.index] = card
        } else {
            throw RuntimeException("tried adding a card to an already full slot")
        }
    }
    fun takeCard(card : Card) {
        val firstIndex = cards.indexOfFirst{it == null}
        if( firstIndex > -1 )
            cards[firstIndex] = card
        else
            throw Exception("tried to draw a card, but hand was full")
    }

    fun size() =cards.filterNotNull().size
    override fun toString() = cards.joinToString(",")

    fun validateBeforePlay(slot: Slot, fx: (Card) -> Unit) {
        rules.youCannotPlayFromAnEmptyHandSlot(slot)
        val card = cards[slot.index]!!
        fx(card)
    }
    fun toDto() = Hand(cards.map { it?.CardDtoConverter()?.convert() })


}