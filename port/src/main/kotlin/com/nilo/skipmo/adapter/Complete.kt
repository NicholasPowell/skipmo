package com.nilo.skipmo.adapter

import java.util.*

abstract class Complete {
    data class GameDTO
    constructor(
            val playerDTOS: List<PlayerDTO>,
            val maxHandSize: Int,
            val pileSize: Int,
            val deckDTO: DeckDTO,
            val buildPileDTO: List<BuildPileDTO>,
            val id: UUID,
            var version: Long,
            var winner: PlayerDTO? = null)
    data class BuildPileDTO(val cardDTOS: MutableList<CardDTO>)
    data class CardDTO(val number: Int)
    data class DeckDTO(val cardDTOS: MutableList<CardDTO>, val discards: MutableList<CardDTO>)
    data class DiscardPileDTO(val cardDTOS: MutableList<CardDTO>)
    data class HandDTO(var cardDTOS: ArrayList<CardDTO?>)
    data class PlayerDTO(
            val name: String,
            val handDTO: HandDTO,
            val stockPileDTO: StockPileDTO,
            val id: UUID,
            val discardPileDTOS: MutableList<DiscardPileDTO>)
    data class StockPileDTO(val cardDTOS: MutableList<CardDTO>)
}