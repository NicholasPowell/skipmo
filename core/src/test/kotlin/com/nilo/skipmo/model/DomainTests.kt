package com.nilo.skipmo.model

import com.nilo.skipmo.api.domain.BuildPileIndex
import com.nilo.skipmo.api.domain.DiscardPileIndex
import com.nilo.skipmo.api.domain.HandSlotIndex
import com.nilo.skipmo.model.domain.Game
import com.nilo.skipmo.model.domain.game.Card
import com.nilo.skipmo.model.domain.game.Deck
import com.nilo.skipmo.model.domain.game.Player
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DomainTests {

    @Test
    fun playFromStock() {
        val game = Game("Nick", "Lori")
        val nick = game.players[0]

        nick.stockPile.addToTop(Card(0))
        nick.stockPile.addToTop(Card(1))

        nick.playFromStockPile(BuildPileIndex.ONE)
        nick.playFromStockPile(BuildPileIndex.ONE)
    }

    @Test
    fun expectExceptionPlayFromStock() {
        val emptyDeck = Deck()
        val game = Game(listOf(Player("Nick")), deck = emptyDeck)
        val nick = game.players[0]

        assertThrows<Exception> {
            nick.discard(HandSlotIndex.ONE, DiscardPileIndex.ONE)
        }
        assertThrows<Exception> {
            nick.playFromDiscardToBuildPile(DiscardPileIndex.ONE, BuildPileIndex.ONE)
        }
        assertThrows<Exception> {
            nick.draw(emptyDeck::drawTopCard)
        }
        assertThrows<Exception> {
            nick.playFromDiscardToBuildPile(DiscardPileIndex.ONE, BuildPileIndex.ONE)
        }

    }

    @Test
    fun playingCardsOnAPile() {

        val game = Game("Nick", "Lori", "David")
        val nick = game.players[0]

        val deck = game.deck
        game.deal()

        nick.hand.play(HandSlotIndex.ONE)
        nick.hand.takeCard(HandSlotIndex.ONE, Card(1))
        nick.playFromHandToBuildPile(HandSlotIndex.ONE, BuildPileIndex.ONE)

        nick.hand.takeCard(HandSlotIndex.ONE, Card(0))
        nick.playFromHandToBuildPile(HandSlotIndex.ONE, BuildPileIndex.ONE)

        nick.hand.takeCard(HandSlotIndex.ONE, Card(0))
        nick.playFromHandToBuildPile(HandSlotIndex.ONE, BuildPileIndex.TWO)

        nick.hand.takeCard(HandSlotIndex.ONE, Card(2))
        nick.playFromHandToBuildPile(HandSlotIndex.ONE, BuildPileIndex.TWO)

        nick.hand.takeCard(HandSlotIndex.ONE, Card(0))
        nick.playFromHandToBuildPile(HandSlotIndex.ONE, BuildPileIndex.TWO)

        nick.hand.takeCard(HandSlotIndex.ONE, Card(4))
        nick.playFromHandToBuildPile(HandSlotIndex.ONE, BuildPileIndex.TWO)

        println(game)

    }

    @Test
    fun playFromDiscardPile() {
        val game = Game("Nick")
        val nick = game.players[0]
        game.deal()

        // Clear these visibleCards out manually
        nick.hand.play(HandSlotIndex.ONE)
        nick.hand.play(HandSlotIndex.TWO)
        nick.hand.play(HandSlotIndex.THREE)

        // Draw three specific visibleCards
        nick.hand.takeCard(HandSlotIndex.ONE, Card(1))
        nick.hand.takeCard(HandSlotIndex.TWO, Card(2))
        nick.hand.takeCard(HandSlotIndex.THREE, Card(3))

        // Play them in order to the same disard pile
        nick.discard(HandSlotIndex.THREE, DiscardPileIndex.ONE) // 3
        nick.discard(HandSlotIndex.TWO, DiscardPileIndex.ONE) // 2
        nick.discard(HandSlotIndex.ONE, DiscardPileIndex.ONE) // 1

        println(game)

        // Play from discard to build pile
        nick.playFromDiscardToBuildPile(DiscardPileIndex.ONE, BuildPileIndex.ONE)
        nick.playFromDiscardToBuildPile(DiscardPileIndex.ONE, BuildPileIndex.ONE)
        nick.playFromDiscardToBuildPile(DiscardPileIndex.ONE, BuildPileIndex.ONE)

        println(game)

    }


}