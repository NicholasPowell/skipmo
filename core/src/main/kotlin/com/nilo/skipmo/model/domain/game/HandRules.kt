package com.nilo.skipmo.model.domain.game

import com.nilo.skipmo.api.domain.HandSlotIndex

class HandRules {
    fun youCannotPlayFromAnEmptyHandSlot(slot: HandSlotIndex, hand: Hand): Card {
        with(hand) {
            val card = cards[slot.index]
            if (cards[slot.index] == null) {
                throw Exception("You cannot play from an empty visibleHand slot")
            }
            return card!!
        }
    }

}