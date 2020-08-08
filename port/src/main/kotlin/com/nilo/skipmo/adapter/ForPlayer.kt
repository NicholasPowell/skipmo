package com.nilo.skipmo.adapter

import java.util.*

abstract class ForPlayer {

    data class GameView(
            val id: UUID,
            val buildPile: List<BuildPileView>,
            val players: List<PlayerView>,
            var currentPlayer: PlayerView,
            var winner: String?,
            var version: Long
    )
    data class BuildPileView(val topCardEffectiveNumber: Int)
    data class CardSlotView(val card: CardView?)
    data class CardView(val number: Int)
    data class DiscardPileView(val visibleCards: List<CardView>)
    data class HandView(val visibleCards: List<CardSlotView>)
    data class PlayerView(val name: String,
                          val visibleHand: HandView,
                          val visibleStockPile: StockPileView,
                          val visibleDiscardPiles: List<DiscardPileView>)
    data class StockPileView(val visibleCards: List<CardView>)
}