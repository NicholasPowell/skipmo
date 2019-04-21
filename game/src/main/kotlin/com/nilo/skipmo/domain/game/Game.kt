package com.nilo.skipmo.domain.game

import com.nilo.skipmo.domain.public.Game
import org.springframework.data.annotation.Id
import java.util.*


internal class Game(
        vararg playerNames : String,
        val maxHandSize : Int = Hand.Slot.values().size,
        val pileSize : Int = 30,
        val deck : Deck = Deck(),
        val buildPile : List<BuildPile> = listOf(
                BuildPile(deck),
                BuildPile(deck),
                BuildPile(deck),
                BuildPile(deck)),
        @Id val id: UUID = UUID.randomUUID(),
        val win : (player : Player) -> Unit = { player -> println("Player ${player.name} wins!!!") }
) {

    val players : MutableList<Player> = playerNames.map{ Player(it, this) }.toMutableList()
    var turns : ListIterator<Player> = players.listIterator()
    var currentPlayer = turns.next()

    fun winner() {
        win(currentPlayer)
    }

    fun deal() {
        populateDeck()
        deck.shuffle()
        players.forEach(::fillHandAndStockPile)
    }

    private fun fillHandAndStockPile(player: Player) {
        player.drawToFull(deck)
        repeat( pileSize) {player.stockPile.addToTop(deck.drawTopCard())}
    }

    fun buildPile(index: BuildPileIndex) : BuildPile {
        return buildPile[index.index]
    }

    fun endTurn( ) {
        if( !turns.hasNext() ) {
            resetTurnIterator()
        }
        currentPlayer = turns.next()
    }

    private fun populateDeck() {
        addSkipMos()
        addNumberedCards()
    }

    private fun resetTurnIterator() {
        turns = players.listIterator()
    }
    private fun addSkipMos() = repeat( 8 ) {deck.addToTop(Card(0))}
    private fun addNumberedCards() = repeat( 12 ) {(1..12).forEach { n -> deck.addToTop(Card(n)) } }

    override fun toString(): String {
        return "config[maxHandSize=${maxHandSize},pileSize=${pileSize},players=${players.size}]\n" +
                "deck: [${deck}] \n" +
                "buildPile: ${buildPile.joinToString(",", transform = {it.getDisplay().display(DisplayContext.DEBUG)}) } \n" +
                "players: \n${players.joinToString("\n", transform = {it.getDisplay().display(DisplayContext.DEBUG)} )} \n"
    }

    fun toDto() = Game(deck.toDto(), buildPile.map { it.getConverter().convert() }, id, players.map { it.getConverter().toDto() }, currentPlayer.name)
}
