package com.nilo.skipmo.domain.game

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StockPileTests {
    @Test
    fun winTest() {
        val stockPile = StockPile()
        stockPile.addToTop(Card(1))
        val buildPile = BuildPile(Deck())
        var called = false
        stockPile.play(buildPile) {
            called = true
        }
        Assertions.assertTrue(called)
    }
}