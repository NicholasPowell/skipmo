package com.nilo.skipmo.domain.game

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class BuildPileTests {

    private val legal = BuildPileLegalMoves()
    private val illegal = BuildPileIllegalMoves()

    @TestFactory
    fun playLegalMoves(): List<DynamicTest> {
        return legal.moves.map { (card, top) ->
            createTest(card, top) {
                playCard(createBuildPile(top), card)
            }
        }
    }

    @TestFactory
    fun playIllegalMoves(): List<DynamicTest> {
        return illegal.moves.map { (card, top) ->
            createTest(card, top) {
                throwsExceptionWhen {
                    playCard(createBuildPile(top), card)
                }
            }
        }
    }

    private fun createTest(card: Card, topCard: Int, function: () -> Unit): DynamicTest {
        return DynamicTest.dynamicTest("BuildPile play card: $card on top of $topCard ") {
            function.invoke()
        }
    }

    private fun throwsExceptionWhen(function: () -> Unit) {
        Assertions.assertThrows(Exception::class.java) {
            function.invoke()
        }
    }

    private fun createBuildPile(topValue: Int) = BuildPile(Deck()).getHelper().withSize(topValue)

    @Test
    @DisplayName("Playing a twelfth card on the BuildPile should cause it to clear")
    fun playTwelve() {
        val deck = Deck()
        val buildPile = BuildPile(deck).getHelper().withSize(11)
        Assertions.assertEquals(11, buildPile.size())
        Assertions.assertEquals(0, deck.discards.size)
        playCard(buildPile, Card(12))
        Assertions.assertEquals(0, buildPile.size())
        Assertions.assertEquals(12, deck.discards.size)
        Assertions.assertEquals(0, deck.size())
    }

    @Test
    @DisplayName("Making an invalid move should not cause a card to be removed from your hand")
    fun proveInvalidMoveKeepsSourceUnchanged() {
        val hand = Hand()
        hand.takeCard(Card(2))
        Assertions.assertEquals(2, hand.peek(Hand.Slot.ONE)?.number)
        val buildPile = BuildPile(Deck()).getHelper().withSize(2)
        Assertions.assertThrows(Exception::class.java) {
            val card : Card = hand.peek(Hand.Slot.ONE)!!
            buildPile.play(card) {
                hand.play(Hand.Slot.ONE)
            }
        }
        Assertions.assertEquals(2, hand.peek(Hand.Slot.ONE)?.number)
    }

    private fun playCard(buildPile: BuildPile, card: Card) {
        buildPile.play(card) { card }
    }

    @Test
    fun proveValidMoveRemovesCardFromHand() {
        val hand = Hand()
        hand.takeCard(Card(2))

        val buildPile = BuildPile(Deck()).getHelper().withSize(1)

        Assertions.assertEquals(1, buildPile.size() )
        Assertions.assertEquals(2, hand.peek(Hand.Slot.ONE)?.number)

        val card : Card = hand.peek(Hand.Slot.ONE)!!
        buildPile.play(card) {
            hand.play(Hand.Slot.ONE)
        }
        Assertions.assertEquals(2, buildPile.size() )
        Assertions.assertEquals(0, hand.size())
    }

    @Test
    fun playingASkipmoTakesOnTheEffectiveValueOfPreviousCardPlusOne() {

        val game = Game("Nick")
        val player = game.currentPlayer

        (0..10).forEach { game.deck.addToTop(Card(it) ) }

        player.draw{Card(0)}

        val buildPile = BuildPile(Deck()).getHelper().withSize(1)

        Assertions.assertEquals(1, buildPile.size() )

        player.playFromHandToBuildPile( Hand.Slot.ONE, buildPile, game.deck)

        assertEquals( 2, buildPile.size() )

    }

}