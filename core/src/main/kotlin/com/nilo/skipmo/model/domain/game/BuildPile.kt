package com.nilo.skipmo.model.domain.game

import com.fasterxml.jackson.annotation.JsonIgnore
import com.nilo.skipmo.model.domain.Game

internal data class BuildPile(
        val cards: MutableList<Card> = mutableListOf()
) {

    @JsonIgnore
    lateinit var game: Game

    @JsonIgnore
    val rules: Rules = Rules()

    fun play(card: Card, playCard: () -> Card) {
        rules.validateLegalToPlay(card)
        addToTop(playCard())
        rules.ifPileIsFullClearIt()
    }

    fun size() = cards.size
    fun getHelper() = BuildPileHelper(this) { addToTop(it) }
    private fun addToTop(card: Card) = cards.add(0, card)

    inner class Rules {

        fun ifPileIsFullClearIt() {
            if (isPileFull()) {
                cards.forEach(::addIfNonNull)
                cards.clear()
            }
        }

        fun validateLegalToPlay(card: Card) {
            if (!isPileEmpty())
                youMayOnlyPlayACardOneHigherThanTheTopCardOrASkipMo(card)
            else
                youMayPlayOnlyAOneOrSkipMoOnAnEmptyPile(card)
        }

        private fun youMayPlayOnlyAOneOrSkipMoOnAnEmptyPile(card: Card) {
            if (!isCardSkipMoOrOne(card))
                throw Exception("invalid card number played on empty pile")
        }

        private fun youMayOnlyPlayACardOneHigherThanTheTopCardOrASkipMo(card: Card) {
            doIfTopCardIsNotNull {
                if (!isCardSkipMoOrOneMoreThanTop(card))
                    throw Exception("invalid card number played on pile")
            }

        }

        private fun doIfTopCardIsNotNull(doThis: (Card) -> Unit) = cards[0].let { doThis(it) }
        private fun isPileFull() = cards.size == 12
        private fun isPileEmpty(): Boolean = cards.size == 0
        private fun addIfNonNull(card: Card?) {
            card?.let(game.deck.discards::add)
        }

        private fun isCardSkipMoOrOne(card: Card) = card.isSkipMo() || card.number == 1
        private fun isCardSkipMoOrOneMoreThanTop(card: Card) =
                card.isSkipMo() || card.number == getTopEffectiveNumber() + 1

        fun getTopEffectiveNumber() = cards.size
    }

}

