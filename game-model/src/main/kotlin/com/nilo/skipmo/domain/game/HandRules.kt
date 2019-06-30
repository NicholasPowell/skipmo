package com.nilo.skipmo.domain.game

internal class HandRules(
        val cards : Array<Card?>,
        val handleException: (String) -> Unit ) {

    fun youCannotPlayFromAnEmptyHandSlot(slot : Hand.Slot) : Card {
        val card = cards[slot.index]
        if( cards[slot.index] == null ) {
            handleException("You cannot play from an empty hand slot")
        }
        return card!!
    }

}