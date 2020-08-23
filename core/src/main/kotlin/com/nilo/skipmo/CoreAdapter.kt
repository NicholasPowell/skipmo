package com.nilo.skipmo

import com.nilo.skipmo.adapter.*
import com.nilo.skipmo.api.domain.BuildPileIndex
import com.nilo.skipmo.api.domain.DiscardPileIndex
import com.nilo.skipmo.api.domain.HandSlotIndex
import com.nilo.skipmo.model.domain.Game
import com.nilo.skipmo.model.domain.game.Player

class CoreAdapter : Port {

    override fun createGame(p1: String, p2: String): GamePort = GameAdapter(p1, p2)

    internal inner class GameAdapter(p1: String, p2: String) : GamePort {
        val game = Game(p1, p2)

        override fun getDisplay() = Complete.GameDTO(
                playerDTOS = game.players.map{
                            Complete.PlayerDTO(
                                    name = it.name,
                                    handDTO = Complete.HandDTO(
                                            cardDTOS = it.hand.cards.filterNotNull()
                                                    .map{ card ->  Complete.CardDTO(number = card.number) } ),
                                    stockPileDTO = Complete.StockPileDTO(
                                            cardDTOS = it.stockPile.cards.map{ card -> Complete.CardDTO(card.number) } ),
                                    id = it.id,
                                    discardPileDTOS = it.discardPiles.map {
                                        discardPile -> Complete.DiscardPileDTO(
                                            cardDTOS = discardPile.cards.map{card->Complete.CardDTO(card.number)} )
                                    }
                            )
                        }
                ,
                maxHandSize = game.maxHandSize,
                pileSize = game.pileSize,
                deckDTO = Complete.DeckDTO(
                        cardDTOS = game.deck.cards.map{ card->Complete.CardDTO(card.number)},
                        discards = game.deck.discards.map{ card->Complete.CardDTO(card.number)}),
                buildPileDTO = listOf(),
                id = game.id,
                version = game.version,
                winner = game.winner?.name
        )

        override fun getPlayerName() = game.currentPlayer.name

        override fun withPlayer(id: String) = if( game.currentPlayer.name == id) PlayerAdapter(game.currentPlayer) else throw Exception("Not current player")

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
