package com.nilo.skipmo.domain.game

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DomainTests {

    @Test
    fun playFromStock() {
        val game = Game("Nick", "Lori")
        val nick = game.players[0]

        nick.stockPile.addToTop(Card(0))
        nick.stockPile.addToTop(Card(1))

        nick.playFromStockPile(game.buildPile(BuildPileIndex.ONE))
        nick.playFromStockPile(game.buildPile(BuildPileIndex.ONE))
    }

    @Test
    fun expectExceptionPlayFromStock() {
        val game = Game("Nick")
        val nick = game.players[0]

        var exception:Exception =assertThrows{
            nick.discard( Hand.Slot.ONE, DiscardPile.Index.ONE)
        }
        exception = assertThrows {
            nick.playFromDiscardToBuildPile( DiscardPile.Index.ONE, game.buildPile(BuildPileIndex.ONE ))
        }
        exception = assertThrows {
            nick.draw(game.deck::drawTopCard)
        }
        exception = assertThrows {
            nick.playFromDiscardToBuildPile(DiscardPile.Index.ONE, game.buildPile(BuildPileIndex.ONE))
        }


    }

    @Test
    fun playingCardsOnAPile() {

        val game = Game("Nick", "Lori", "David")
        val nick = game.players[0]

        val deck = game.deck
        game.deal()

        nick.hand.play(Hand.Slot.ONE)
        nick.hand.takeCard(Hand.Slot.ONE, Card(1))
        nick.playFromHandToBuildPile(Hand.Slot.ONE, game.buildPile[0], deck )

        nick.hand.takeCard(Hand.Slot.ONE, Card(0))
        nick.playFromHandToBuildPile(Hand.Slot.ONE, game.buildPile[0], deck )

        nick.hand.takeCard(Hand.Slot.ONE, Card(0))
        nick.playFromHandToBuildPile(Hand.Slot.ONE, game.buildPile[1], deck )

        nick.hand.takeCard(Hand.Slot.ONE, Card(2))
        nick.playFromHandToBuildPile(Hand.Slot.ONE, game.buildPile[1], deck )

        nick.hand.takeCard(Hand.Slot.ONE, Card(0))
        nick.playFromHandToBuildPile(Hand.Slot.ONE, game.buildPile[1], deck )

        nick.hand.takeCard(Hand.Slot.ONE, Card(4))
        nick.playFromHandToBuildPile(Hand.Slot.ONE, game.buildPile[1], deck )

        println( game )

    }

    @Test
    fun playFromDiscardPile() {
        val game = Game("Nick")
        val nick = game.players[0]
        game.deal()

        // Clear these cards out manually
        nick.hand.play(Hand.Slot.ONE)
        nick.hand.play(Hand.Slot.TWO)
        nick.hand.play(Hand.Slot.THREE)

        // Draw three specific cards
        nick.hand.takeCard(Hand.Slot.ONE, Card(1))
        nick.hand.takeCard(Hand.Slot.TWO, Card(2))
        nick.hand.takeCard(Hand.Slot.THREE, Card(3))

        // Play them in order to the same disard pile
        nick.discard(Hand.Slot.THREE, DiscardPile.Index.ONE) // 3
        nick.discard(Hand.Slot.TWO, DiscardPile.Index.ONE) // 2
        nick.discard(Hand.Slot.ONE, DiscardPile.Index.ONE) // 1

        println(game)

        // Play from discard to build pile
        nick.playFromDiscardToBuildPile(DiscardPile.Index.ONE, game.buildPile[0] )
        nick.playFromDiscardToBuildPile(DiscardPile.Index.ONE, game.buildPile[0] )
        nick.playFromDiscardToBuildPile(DiscardPile.Index.ONE, game.buildPile[0] )

        println(game)

    }


}