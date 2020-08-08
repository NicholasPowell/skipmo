package com.nilo.skipmo.api.domain

import com.nilo.skipmo.model.actions.AppliesToPlayer
import com.nilo.skipmo.model.domain.game.Player

internal class PublicAction(val move: Move) {

    sealed class Move : AppliesToPlayer {
        data class PlayFromHand(
                val handSlot: HandSlotIndex,
                val buildPileIndex: BuildPileIndex) : Move() {
            override fun applyTo(player: Player) {
                player.playFromHandToBuildPile(handSlot, buildPileIndex)
            }
        }

        data class PlayFromDiscard(
                val discardPileIndex: DiscardPileIndex,
                val buildPileIndex: BuildPileIndex) : Move() {
            override fun applyTo(player: Player) {
                player.playFromDiscardToBuildPile(discardPileIndex, buildPileIndex)
            }
        }

        data class PlayFromStockPile(
                val buildPileIndex: BuildPileIndex) : Move() {
            override fun applyTo(player: Player) {
                player.playFromStockPile(buildPileIndex)
            }
        }

        data class ReorderHand(val firstSlot: HandSlotIndex,
                                        val secondSlot: HandSlotIndex) : Move() {

            override fun applyTo(player: Player) {
                player.reorderHand(firstSlot, secondSlot)
            }
        }

        class RedrawHand : Move() {
            override fun applyTo(player: Player) {
                player.drawToFull()
            }
        }

        class DiscardToPile(
                val handSlot: HandSlotIndex,
                val discardPileIndex: DiscardPileIndex) : AppliesToPlayer, Move() {
            override fun applyTo(player: Player) {
                player.discard(handSlot, discardPileIndex)
            }
        }
//        class Win : Move() {
//            override fun applyTo(player: Player) {
//                player.game.win(player)
//            }
//        }
        class Surrender : Move() {

            override fun applyTo(player: Player) {

            }
        }
    }

    enum class Index(val offset: Int) {
        ONE(0),
        TWO(1),
        THREE(2),
        FOUR(3),
        FIVE(4)
    }
}


