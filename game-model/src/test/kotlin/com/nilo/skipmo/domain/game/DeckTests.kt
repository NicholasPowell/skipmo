package com.nilo.skipmo.domain.game

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DeckTests {

    @Test
    fun rotateDiscard() {
        val deck = Deck()
        deck.discards.add(Card(1))
        val player = Game("Nick").players[0]
        player.draw{
            deck.drawTopCard()
        }
        Assertions.assertEquals(1, player.hand.peek(Hand.Slot.ONE)!!.number )
    }

    @Test
    fun drawTopCard() {
        val deck = Deck(mutableListOf(Card(1)))
        Assertions.assertEquals(1, deck.drawTopCard().number)
        Assertions.assertEquals(0, deck.size())
    }
    @Test
    fun drawEmptyThrowsException() {
        val deck = Deck(mutableListOf())
        Assertions.assertThrows(Exception::class.java) {
            deck.drawTopCard()
        }
    }

    @Test
    fun drawEmptyPullsFromDiscard() {
        val deck = Deck(mutableListOf())
        deck.discards.add(Card(1))
        Assertions.assertEquals( 1, deck.drawTopCard().number )
        Assertions.assertEquals( 0, deck.discards.size )
    }

}