package com.nilo.skipmo.model.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.nilo.skipmo.api.domain.BuildPileIndex
import com.nilo.skipmo.api.domain.HandSlotIndex
import com.nilo.skipmo.model.domain.game.BuildPile
import com.nilo.skipmo.model.domain.game.Deck
import com.nilo.skipmo.model.domain.game.Player
import java.util.*

internal data class Game(
        val players: List<Player>,
        val maxHandSize: Int = HandSlotIndex.values().size,
        val pileSize: Int = 30,
        val deck: Deck = Deck().populated().shuffled(),
        val buildPile: List<BuildPile> = listOf(
                BuildPile(),
                BuildPile(),
                BuildPile(),
                BuildPile()),
        val id: UUID = UUID.randomUUID(),
        var version: Long = 0L,
        var winner: Player? = null

) {

    private var turnIter: ListIterator<Player> = players.listIterator(0)
    val win: (player: Player) -> Unit = { player ->
        println("PlayerView ${player.name} wins!!!")
        winner = player
    }

    constructor(vararg playerNames: String) :
            this(playerNames.map { Player(it) })

    init {
        players.forEach { it.game = this }
        buildPile.forEach { it.game = this }
    }

    @JsonIgnore
    var currentPlayer = turnIter.next()

    fun winner() {
        win(currentPlayer)
    }

    fun deal() {
        players.forEach(::fillHandAndStockPile)
    }

    fun fillHandAndStockPile(player: Player) {
        player.drawToFull()
        repeat(pileSize) { player.stockPile.addToTop(deck.drawTopCard()) }
    }

    fun buildPile(index: BuildPileIndex): BuildPile {
        return buildPile[index.index]
    }

    fun endTurn() {
        if (!turnIter.hasNext()) {
            turnIter = players.listIterator(0)
        }

        currentPlayer = turnIter.next()
    }
}
