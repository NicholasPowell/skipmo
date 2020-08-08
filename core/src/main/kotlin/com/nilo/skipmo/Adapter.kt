package com.nilo.skipmo

import com.nilo.skipmo.adapter.GamePort
import com.nilo.skipmo.adapter.PlayerPort
import com.nilo.skipmo.adapter.Port
import com.nilo.skipmo.adapter.SrcDest
import com.nilo.skipmo.api.domain.BuildPileIndex
import com.nilo.skipmo.api.domain.DiscardPileIndex
import com.nilo.skipmo.api.domain.HandSlotIndex
import com.nilo.skipmo.model.domain.Game
import com.nilo.skipmo.model.domain.game.Player

class Adapter : Port {

    override fun createGame(p1: String, p2: String): GamePort {
        return GameAdapter(p1, p2)
    }

    internal inner class GameAdapter(val p1: String, val p2: String) : GamePort {
        val game = Game(p1, p2)

        override fun getPlayerName() = game.currentPlayer.name

        override fun withPlayer(name: String) = if( game.currentPlayer.name == name) PlayerAdapter(game.currentPlayer) else throw Exception("Not current player")

        internal inner class PlayerAdapter(private val player: Player) : PlayerPort {
            override fun draw(): GamePort {
                player.drawToFull()
                return this@GameAdapter
            }

            override fun moveCardInHand(srcDest: SrcDest): GamePort {
                player.hand.swap(
                        HandSlotIndex.valueOf(srcDest.src),
                        HandSlotIndex.valueOf(srcDest.dest)
                )
                return this@GameAdapter
            }

            override fun playFromDiscardToBuildPile(srcDest: SrcDest): GamePort {
                player.playFromDiscardToBuildPile(
                        DiscardPileIndex.valueOf(srcDest.src),
                        BuildPileIndex.valueOf(srcDest.dest)
                )
                return this@GameAdapter
            }

            override fun playFromHandToBuildPile(srcDest: SrcDest): GamePort {
                player.playFromHandToBuildPile(
                        HandSlotIndex.valueOf(srcDest.src),
                        BuildPileIndex.valueOf(srcDest.dest)
                )
                return this@GameAdapter
            }

            override fun playFromStockPile(src: String): GamePort {
                player.playFromStockPile(
                        BuildPileIndex.valueOf(src)
                )
                return this@GameAdapter
            }

            override fun discard(srcDest: SrcDest): GamePort {
                player.discard(
                        HandSlotIndex.valueOf(srcDest.src),
                        DiscardPileIndex.valueOf(srcDest.dest)
                )
                return this@GameAdapter
            }
        }
    }
}
