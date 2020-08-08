package com.nilo.skipmo.model.domain.game

import com.nilo.skipmo.api.domain.BuildPileIndex
import com.nilo.skipmo.api.domain.HandSlotIndex
import com.nilo.skipmo.model.domain.Game

internal interface Move : (Game) -> Game
internal class PlayOnBuildPile(val buildPileIndex: BuildPileIndex, val handSlot: HandSlotIndex) : Move {
    override fun invoke(game: Game) = game.apply {
        game.currentPlayer.playFromHandToBuildPile(handSlot, buildPileIndex)
    }

    override fun toString(): String {
        return "PlayOnBuildPile: $handSlot, $buildPileIndex"
    }
}

internal class LegalMoveResolver(val game: Game) {

    fun validMoves(): List<Move> {
        val moves: MutableList<Move> = mutableListOf()
        val currentPlayer = game.currentPlayer
        val hand = currentPlayer.hand
        for (x in BuildPileIndex.values()) {
            for (y in HandSlotIndex.values()) {
                if (validate(game.buildPile(x), hand, HandSlotIndex.ONE)) {
                    moves.add(PlayOnBuildPile(x, y))
                }
            }
        }
        return moves
    }

    fun validate(buildPile: BuildPile, hand: Hand, slot: HandSlotIndex): Boolean {
        try {
            buildPile.rules.validateLegalToPlay(hand.peek(slot)!!)
            return true
        } catch (e: Exception) {
            return false
        }
    }


}