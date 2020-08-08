package com.nilo.skipmo.model.domain.game

import com.nilo.skipmo.api.domain.HandSlotIndex

internal class PlayerRules {

    fun youCannotDiscardFromAnEmptyHandSlot(handSlot: HandSlotIndex, hand: Hand) {
        Hand.rules.youCannotPlayFromAnEmptyHandSlot(handSlot, hand)
    }

    fun ifYouPlayAllOfYourCardsYouMustDrawANewHand(hand: Hand, player: Player) {
        if (hand.size() == 0)
            player.drawToFull()
    }
}