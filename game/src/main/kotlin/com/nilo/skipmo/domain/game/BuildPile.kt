package com.nilo.skipmo.domain.game

internal class BuildPile(private val deck : Deck) {

    private val cards : MutableList<Card> = mutableListOf()
    private val rules : BuildPileRules = BuildPileRules(deck, cards) { message: String -> throw Exception(message) }

    fun play(card: Card, playCard : () -> Card) {
        rules.validateLegalToPlay(card)
        addToTop( playCard() )
        rules.ifPileIsFullClearIt()
    }

    fun size() = cards.size
    fun getHelper() = BuildPileHelper(this ) { addToTop(it) }
    fun getDisplay() = BuildPileDisplay(this.cards)
    fun getConverter() = BuildPileDtoConverter(deck,cards)

    private fun addToTop( card : Card) = cards.add(0,card)
}

