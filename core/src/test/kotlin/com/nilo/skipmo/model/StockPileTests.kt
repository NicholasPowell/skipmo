package com.nilo.skipmo.model

import com.nilo.skipmo.model.domain.Game
import com.nilo.skipmo.model.domain.game.BuildPile
import com.nilo.skipmo.model.domain.game.Card
import com.nilo.skipmo.model.domain.game.StockPile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StockPileTests {
    @Test
    fun winTest() {
        val stockPile = StockPile()
        stockPile.addToTop(Card(1))
        val buildPile = BuildPile().also { it.game = Game("Nick") }
        var called = false
        stockPile.play(buildPile) {
            called = true
        }
        Assertions.assertTrue(called)
    }
}