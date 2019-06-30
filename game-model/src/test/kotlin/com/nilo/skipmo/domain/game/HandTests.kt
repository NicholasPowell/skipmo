package com.nilo.skipmo.domain.game

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class HandTests {


    private val drawToFullCombinations = listOf(
            listOf(),
            listOf( Hand.Slot.ONE ),
            listOf( Hand.Slot.ONE, Hand.Slot.TWO ),
            listOf( Hand.Slot.ONE, Hand.Slot.TWO, Hand.Slot.THREE ),
            listOf( Hand.Slot.ONE, Hand.Slot.TWO, Hand.Slot.THREE, Hand.Slot.FOUR ),
            listOf( Hand.Slot.ONE, Hand.Slot.TWO, Hand.Slot.THREE, Hand.Slot.FOUR, Hand.Slot.FIVE ),
            listOf( Hand.Slot.ONE, Hand.Slot.TWO, Hand.Slot.THREE, Hand.Slot.FIVE ),
            listOf( Hand.Slot.ONE, Hand.Slot.TWO, Hand.Slot.FOUR ),
            listOf( Hand.Slot.ONE, Hand.Slot.TWO, Hand.Slot.FOUR, Hand.Slot.FIVE ),
            listOf( Hand.Slot.ONE, Hand.Slot.TWO, Hand.Slot.FIVE ),
            listOf( Hand.Slot.TWO ),
            listOf( Hand.Slot.TWO, Hand.Slot.THREE ),
            listOf( Hand.Slot.TWO, Hand.Slot.THREE, Hand.Slot.FOUR ),
            listOf( Hand.Slot.TWO, Hand.Slot.THREE, Hand.Slot.FOUR, Hand.Slot.FIVE ),
            listOf( Hand.Slot.TWO, Hand.Slot.THREE, Hand.Slot.FIVE ),
            listOf( Hand.Slot.TWO, Hand.Slot.FOUR ),
            listOf( Hand.Slot.TWO, Hand.Slot.FOUR, Hand.Slot.FIVE ),
            listOf( Hand.Slot.TWO, Hand.Slot.FIVE ),
            listOf( Hand.Slot.THREE ),
            listOf( Hand.Slot.THREE, Hand.Slot.FOUR ),
            listOf( Hand.Slot.THREE, Hand.Slot.FOUR, Hand.Slot.FIVE ),
            listOf( Hand.Slot.THREE, Hand.Slot.FIVE ),
            listOf( Hand.Slot.FOUR, Hand.Slot.FIVE ),
            listOf( Hand.Slot.FOUR ),
            listOf( Hand.Slot.FIVE )

    )
    
    @TestFactory
    fun drawToFull()  : List<DynamicTest> {
        val tests : MutableList<DynamicTest> = mutableListOf()
        drawToFullCombinations.forEach { slots : List<Hand.Slot>  ->
            tests.add(DynamicTest.dynamicTest("Draw to full after discarding ${slots.joinToString() }") {
                val deck = Deck()
                IntRange(1, 12).forEach { deck.addToTop(Card(it)) }
                val hand = Hand()
                hand.drawToFull(deck)
                Assertions.assertEquals(5, hand.size())
                slots.forEach{
                    hand.play(it)
                }
                hand.drawToFull(deck)
                Assertions.assertEquals(5, hand.size())
            })
        }
        return tests
    }

    @TestFactory
    fun dontAllowPlayingFromAnEmptyHandSlot() : List<DynamicTest> {
        val tests: MutableList<DynamicTest> = mutableListOf()
        Hand.Slot.values().forEach{
            tests.add(DynamicTest.dynamicTest("Don't allow playing from empty hand slot $it") {
                val hand = Hand()
                Assertions.assertEquals(0, hand.size())
                Assertions.assertThrows(Exception::class.java) {
                    hand.validateBeforePlay(it){}
                }
            })}
        return tests
    }

    @Test
    fun swap() {
        val hand = Hand()
        hand.takeCard( Hand.Slot.ONE, Card(1))
        hand.takeCard( Hand.Slot.TWO, Card(2))
        hand.swap(Hand.Slot.ONE, Hand.Slot.TWO)
        Assertions.assertEquals( 2, hand.peek(Hand.Slot.ONE)?.number )
        Assertions.assertEquals( 1, hand.peek(Hand.Slot.TWO)?.number )
    }

    @Test
    fun swapNull() {
        val hand = Hand()
        hand.takeCard( Hand.Slot.ONE, Card(1))
        hand.swap(Hand.Slot.ONE, Hand.Slot.TWO)
        Assertions.assertEquals( null, hand.peek(Hand.Slot.ONE)?.number )
        Assertions.assertEquals( 1, hand.peek(Hand.Slot.TWO)?.number )
    }

    @Test
    fun swapNullBack() {
        val hand = Hand()
        hand.takeCard( Hand.Slot.TWO, Card(2))
        hand.swap(Hand.Slot.ONE, Hand.Slot.TWO)
        Assertions.assertEquals( 2, hand.peek(Hand.Slot.ONE)?.number )
        Assertions.assertEquals( null, hand.peek(Hand.Slot.TWO)?.number )
    }




}