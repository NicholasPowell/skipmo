package com.nilo.skipmo.domain.game

internal class PlayerRules(val hand: Hand, val player: Player) {

    fun youCannotDiscardFromAnEmptyHandSlot(handSlot : Hand.Slot) {
        hand.rules.youCannotPlayFromAnEmptyHandSlot(handSlot)
    }
    fun ifYouPlayAllOfYourCardsYouMustDrawANewHand(deck : Deck) {
        if( hand.size() == 0 )
            player.drawToFull(deck)
    }
}