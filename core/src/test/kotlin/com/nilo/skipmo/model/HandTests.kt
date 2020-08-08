package com.nilo.skipmo.model

import com.nilo.skipmo.api.domain.HandSlotIndex
import com.nilo.skipmo.model.domain.game.Card
import com.nilo.skipmo.model.domain.game.Deck
import com.nilo.skipmo.model.domain.game.Hand
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class HandTests {


    private val drawToFullCombinations = listOf(
            listOf(),
            listOf(HandSlotIndex.ONE),
            listOf(HandSlotIndex.ONE, HandSlotIndex.TWO),
            listOf(HandSlotIndex.ONE, HandSlotIndex.TWO, HandSlotIndex.THREE),
            listOf(HandSlotIndex.ONE, HandSlotIndex.TWO, HandSlotIndex.THREE, HandSlotIndex.FOUR),
            listOf(HandSlotIndex.ONE, HandSlotIndex.TWO, HandSlotIndex.THREE, HandSlotIndex.FOUR, HandSlotIndex.FIVE),
            listOf(HandSlotIndex.ONE, HandSlotIndex.TWO, HandSlotIndex.THREE, HandSlotIndex.FIVE),
            listOf(HandSlotIndex.ONE, HandSlotIndex.TWO, HandSlotIndex.FOUR),
            listOf(HandSlotIndex.ONE, HandSlotIndex.TWO, HandSlotIndex.FOUR, HandSlotIndex.FIVE),
            listOf(HandSlotIndex.ONE, HandSlotIndex.TWO, HandSlotIndex.FIVE),
            listOf(HandSlotIndex.TWO),
            listOf(HandSlotIndex.TWO, HandSlotIndex.THREE),
            listOf(HandSlotIndex.TWO, HandSlotIndex.THREE, HandSlotIndex.FOUR),
            listOf(HandSlotIndex.TWO, HandSlotIndex.THREE, HandSlotIndex.FOUR, HandSlotIndex.FIVE),
            listOf(HandSlotIndex.TWO, HandSlotIndex.THREE, HandSlotIndex.FIVE),
            listOf(HandSlotIndex.TWO, HandSlotIndex.FOUR),
            listOf(HandSlotIndex.TWO, HandSlotIndex.FOUR, HandSlotIndex.FIVE),
            listOf(HandSlotIndex.TWO, HandSlotIndex.FIVE),
            listOf(HandSlotIndex.THREE),
            listOf(HandSlotIndex.THREE, HandSlotIndex.FOUR),
            listOf(HandSlotIndex.THREE, HandSlotIndex.FOUR, HandSlotIndex.FIVE),
            listOf(HandSlotIndex.THREE, HandSlotIndex.FIVE),
            listOf(HandSlotIndex.FOUR, HandSlotIndex.FIVE),
            listOf(HandSlotIndex.FOUR),
            listOf(HandSlotIndex.FIVE)

    )

    @TestFactory
    fun drawToFull(): List<DynamicTest> {
        val tests: MutableList<DynamicTest> = mutableListOf()
        drawToFullCombinations.forEach { slots: List<HandSlotIndex> ->
            tests.add(DynamicTest.dynamicTest("Draw to full after discarding ${slots.joinToString()}") {
                val deck = Deck()
                IntRange(1, 12).forEach { deck.addToTop(Card(it)) }
                val hand = Hand()
                hand.drawToFull(deck)
                Assertions.assertEquals(5, hand.size())
                slots.forEach {
                    hand.play(it)
                }
                hand.drawToFull(deck)
                Assertions.assertEquals(5, hand.size())
            })
        }
        return tests
    }

    @TestFactory
    fun dontAllowPlayingFromAnEmptyHandSlot(): List<DynamicTest> {
        val tests: MutableList<DynamicTest> = mutableListOf()
        HandSlotIndex.values().forEach {
            tests.add(DynamicTest.dynamicTest("Don't allow playing from empty visibleHand slot $it") {
                val hand = Hand()
                Assertions.assertEquals(0, hand.size())
                Assertions.assertThrows(Exception::class.java) {
                    hand.validateBeforePlay(it) {}
                }
            })
        }
        return tests
    }

    @Test
    fun swap() {
        val hand = Hand()
        hand.takeCard(HandSlotIndex.ONE, Card(1))
        hand.takeCard(HandSlotIndex.TWO, Card(2))
        hand.swap(HandSlotIndex.ONE, HandSlotIndex.TWO)
        Assertions.assertEquals(2, hand.peek(HandSlotIndex.ONE)?.number)
        Assertions.assertEquals(1, hand.peek(HandSlotIndex.TWO)?.number)
    }

    @Test
    fun swapNull() {
        val hand = Hand()
        hand.takeCard(HandSlotIndex.ONE, Card(1))
        hand.swap(HandSlotIndex.ONE, HandSlotIndex.TWO)
        Assertions.assertEquals(null, hand.peek(HandSlotIndex.ONE)?.number)
        Assertions.assertEquals(1, hand.peek(HandSlotIndex.TWO)?.number)
    }

    @Test
    fun swapNullBack() {
        val hand = Hand()
        hand.takeCard(HandSlotIndex.TWO, Card(2))
        hand.swap(HandSlotIndex.ONE, HandSlotIndex.TWO)
        Assertions.assertEquals(2, hand.peek(HandSlotIndex.ONE)?.number)
        Assertions.assertEquals(null, hand.peek(HandSlotIndex.TWO)?.number)
    }


}