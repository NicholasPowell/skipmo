package com.nilo.skipmo.domain.game

//enum class Level { GLOBAL, PLAYER, HIDDEN }
//abstract sealed class Display {
//    abstract fun display(level: Level): String
//}
//
//class BuildPileDisplay(val buildPile: BuildPile): Display() {
//    override fun display(level: Level ): String {
//        return "" + buildPile.cards
//    }
//}
//class CardDisplay(val card: Card): Display() {
//    override fun display(level: Level): String = card.display()
//}
//
//class DeckDisplay(val deck: Deck): Display() {
//    override fun display(level: Level): String {
//        return "deck: " +
//                when(level) {
//                    Level.GLOBAL -> deck.size()
//                    Level.PLAYER -> deck.size()
//                    Level.HIDDEN -> deck.display()
//                }
//    }
//}
//class DiscardPileDisplay(val discardPile: DiscardPile): Display()
//class GameDisplay(val game: Game): Display()
//class HandDisplay(val hand: Hand): Display()
//class PlayerDisplay(val player: Player): Display()
//class StockPileDisplay(val stockPile: StockPile): Display()
