package com.nilo.skipmo.model.domain.game

import com.nilo.skipmo.api.domain.BuildPileIndex
import com.nilo.skipmo.api.domain.DiscardPileIndex
import com.nilo.skipmo.api.domain.HandSlotIndex
import com.nilo.skipmo.model.domain.Game
import java.util.*

internal data class Player(
        val name: String,
        val hand: Hand = Hand(),
        val stockPile: StockPile = StockPile(),
        val id: UUID = UUID.randomUUID(),
        val discardPiles: MutableList<DiscardPile> = mutableListOf(DiscardPile(), DiscardPile(), DiscardPile(), DiscardPile())
) {
    companion object {
        private val rules = PlayerRules()
    }

    internal lateinit var game: Game

    fun reorderHand(slot1: HandSlotIndex, slot2: HandSlotIndex) {
        hand.swap(slot1, slot2)
    }

    fun discard(slot: HandSlotIndex, discardPile: DiscardPileIndex) {
        rules.youCannotDiscardFromAnEmptyHandSlot(slot, hand)
        discardPiles[discardPile.index].addToTop(hand.play(slot))
        drawToFull()
        game.endTurn()
    }

    fun draw(drawFx: () -> Card) {
        hand.takeCard(drawFx())
    }

    fun playFromStockPile(buildPileIndex: BuildPileIndex) {
        stockPile.play(game.buildPile(buildPileIndex), game::winner)
    }

    fun playFromDiscardToBuildPile(discardPileIndex: DiscardPileIndex, buildPile: BuildPileIndex) {
        val discardPile = discardPiles[discardPileIndex.index]
        discardPile.validateBeforePlay()
        game.buildPile(buildPile).play(discardPile.showTopCard()) { discardPile.play() }
    }

    fun playFromHandToBuildPile(slot: HandSlotIndex, buildPileIndex: BuildPileIndex) {
        hand.validateBeforePlay(slot) {
            game.buildPile(buildPileIndex).play(it) { hand.play(slot) }
        }
        rules.ifYouPlayAllOfYourCardsYouMustDrawANewHand(hand, this)
    }

    fun drawToFull() {
        hand.drawToFull(game.deck)
    }

}
