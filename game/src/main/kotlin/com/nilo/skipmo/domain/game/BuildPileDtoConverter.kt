package com.nilo.skipmo.domain.game

internal class BuildPileDtoConverter(private val deck: Deck, private val cards: MutableList<Card>) {
    fun convert() =
            com.nilo.skipmo.domain.public.BuildPile(
                    deck.toDto(),
                    cards.map { it.CardDtoConverter().convert() })
}