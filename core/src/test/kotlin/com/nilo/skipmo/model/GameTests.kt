package com.nilo.skipmo.model

import com.nilo.skipmo.model.domain.Game
import com.nilo.skipmo.model.domain.game.Card
import org.junit.jupiter.api.Test

class GameTests {

    @Test
    fun fullGame() {

        val game = Game("Nick", "Lori")
        repeat(12) { (1..12).forEach { n -> game.deck.addToTop(Card(n)) } }
        repeat(8) { game.deck.addToTop(Card(0)) }
        game.players.forEach { player ->
            repeat(game.maxHandSize) { player.hand.takeCard(game.deck.drawTopCard()) }
            repeat(game.pileSize) { player.stockPile.addToTop(game.deck.drawTopCard()) }
        }
        println(game)


    }
}