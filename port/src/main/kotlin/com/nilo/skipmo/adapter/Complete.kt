package com.nilo.skipmo.adapter

import java.util.*

class Complete {
    data class GameDTO
    constructor(
            val playerDTOS: List<PlayerDTO>,
            val maxHandSize: Int,
            val pileSize: Int,
            val deckDTO: DeckDTO,
            val buildPileDTO: List<BuildPileDTO>,
            val id: UUID,
            var version: Long,
            var winner: String? = null)

    data class BuildPileDTO(val cardDTOS: List<CardDTO>)
    data class CardDTO(val number: Int)
    data class DeckDTO(val cardDTOS: List<CardDTO>, val discards: List<CardDTO>)
    data class DiscardPileDTO(val cardDTOS: List<CardDTO>)
    data class HandDTO(var cardDTOS: List<CardDTO?>)
    data class PlayerDTO(
            val name: String,
            val handDTO: HandDTO,
            val stockPileDTO: StockPileDTO,
            val id: UUID,
            val discardPileDTOS: List<DiscardPileDTO>)

    data class StockPileDTO(val cardDTOS: List<CardDTO>)
}