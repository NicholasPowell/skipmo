package com.nilo.skipmo.domain.game

internal class BuildPileRules(
        private val deck: Deck,
        private val cards: MutableList<Card>,
        private val handleException : (String) -> Unit
) {

    fun ifPileIsFullClearIt() {
        if ( isPileFull() ) {
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

    private fun youMayPlayOnlyAOneOrSkipMoOnAnEmptyPile( card : Card) {
        if( !isCardSkipMoOrOne(card)  )
            handleException("invalid card number played on empty pile")
    }

    private fun youMayOnlyPlayACardOneHigherThanTheTopCardOrASkipMo(card: Card) {
        doIfTopCardIsNotNull{
            if (!isCardSkipMoOrOneMoreThanTop(card))
                handleException("invalid card number played on pile")
        }

    }

    private fun doIfTopCardIsNotNull( doThis: (Card) -> Unit ) = cards[0].let{ doThis(it) }
    private fun isPileFull() = cards.size == 12
    private fun isPileEmpty() : Boolean = cards.size == 0
    private fun addIfNonNull(card: Card?) { card?.let( deck.discards::add ) }
    private fun isCardSkipMoOrOne(card: Card) = card.isSkipMo() || card.number == 1
    private fun isCardSkipMoOrOneMoreThanTop(card: Card) =
            card.isSkipMo() || card.number == getTopEffectiveNumber() + 1
    private fun getTopEffectiveNumber() = cards.size
}

