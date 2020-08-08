package com.nilo.skipmo.model

import com.nilo.skipmo.api.domain.BuildPileIndex
import com.nilo.skipmo.api.domain.HandSlotIndex
import com.nilo.skipmo.model.domain.Game
import com.nilo.skipmo.model.domain.game.BuildPile
import com.nilo.skipmo.model.domain.game.Card
import com.nilo.skipmo.model.domain.game.Hand
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
        return DynamicTest.dynamicTest("BuildPileView play card: $card on top of $topCard ") {
            function.invoke()
        }
    }

    private fun throwsExceptionWhen(function: () -> Unit) {
        Assertions.assertThrows(Exception::class.java) {
            function.invoke()
        }
    }

    private fun createBuildPile(topValue: Int) = BuildPile().apply { game = Game("Nick") }.getHelper().withSize(topValue)

    @Test
    @DisplayName("Playing a twelfth card on the BuildPileView should cause it to clear")
    fun playTwelve() {
        val game = Game("Nick", "Lori")
        val buildPile = BuildPile().also { it.game = game }.getHelper().withSize(11)
        assertEquals(11, buildPile.size())
        assertEquals(0, game.deck.discards.size)
        playCard(buildPile, Card(12))
        assertEquals(0, buildPile.size())
        assertEquals(12, game.deck.discards.size)
    }

    @Test
    @DisplayName("Making an invalid move should not cause a card to be removed from your visibleHand")
    fun proveInvalidMoveKeepsSourceUnchanged() {
        val hand = Hand()
        hand.takeCard(Card(2))
        assertEquals(2, hand.peek(HandSlotIndex.ONE)?.number)
        val buildPile = BuildPile().also { it.game = Game("Nick") }.getHelper().withSize(2)
        Assertions.assertThrows(Exception::class.java) {
            val card: Card = hand.peek(HandSlotIndex.ONE)!!
            buildPile.play(card) {
                hand.play(HandSlotIndex.ONE)
            }
        }
        assertEquals(2, hand.peek(HandSlotIndex.ONE)?.number)
    }

    private fun playCard(buildPile: BuildPile, card: Card) {
        buildPile.play(card) { card }
    }

    @Test
    fun proveValidMoveRemovesCardFromHand() {
        val hand = Hand()
        hand.takeCard(Card(2))

        val buildPile = BuildPile().getHelper().withSize(1)

        assertEquals(1, buildPile.size())
        assertEquals(2, hand.peek(HandSlotIndex.ONE)?.number)

        val card: Card = hand.peek(HandSlotIndex.ONE)!!
        buildPile.play(card) {
            hand.play(HandSlotIndex.ONE)
        }
        assertEquals(2, buildPile.size())
        assertEquals(0, hand.size())
    }

    @Test
    fun playingASkipmoTakesOnTheEffectiveValueOfPreviousCardPlusOne() {

        val game = Game("Nick", "NOBODY")
        val player = game.currentPlayer

        (0..10).forEach { game.deck.addToTop(Card(it)) }

        player.draw { Card(0) }

        val buildPileIndex = BuildPileIndex.ONE
        val buildPile = game.buildPile(buildPileIndex)
        game.buildPile(buildPileIndex).play(Card(1)) { Card(1) }

        assertEquals(1, buildPile.size())

        player.playFromHandToBuildPile(HandSlotIndex.ONE, buildPileIndex)

        assertEquals(2, buildPile.size())

    }

}