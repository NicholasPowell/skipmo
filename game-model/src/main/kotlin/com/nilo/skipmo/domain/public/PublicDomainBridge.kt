package com.nilo.skipmo.domain.public

import org.springframework.stereotype.Service
import java.util.*


data class Card(val number: Int )
data class Deck(val cards : List<Card>, val discards : List<Card> )
data class BuildPile(val deck : Deck, val cards : List<Card?> )
data class Hand(val cards : List<Card?> )
data class StockPile(val cards : List<Card>)
data class DiscardPile(val cards: List<Card>)
data class Player(val name : String,
                  val hand : Hand,
                  val stockPile : StockPile,
                  val discardPiles : List<DiscardPile>)
data class Game(
        val deck : Deck,
        val buildPile : List<BuildPile>,
        val id: UUID = UUID.randomUUID(),
        val players : List<Player>,
        var currentPlayerName: String
)

@Service("gameMaker")
class GameMaker {
    private val validator = Validator()
    fun makeMeAGame(playerNames: List<String> ) : Game {//}, success: (game: Game)-> Unit, handleFailedValidation: () -> Unit) {
//        validator.doIfValid(playerNames, handleFailedValidation) {
            val game = com.nilo.skipmo.domain.game.Game(*playerNames.toTypedArray())
            game.deal()
            return game.toDto()

    }
    private class Validator {
        fun doIfValid(playerNames: List<String>, fail: ()->Unit, handleValidPlayerNames: (playerNames: List<String>)->Unit) {
            if( hasEnoughPlayers(playerNames) )
                handleValidPlayerNames(playerNames)
            else fail()
        }
        private fun hasEnoughPlayers(playerNames: List<String>) = playerNames.size > 1
    }
}



