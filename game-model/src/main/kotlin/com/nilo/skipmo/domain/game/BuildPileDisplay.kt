package com.nilo.skipmo.domain.game

internal class BuildPileDisplay(val cards: MutableList<Card>) {

    fun display(displayContext: DisplayContext) = when(displayContext) {
        DisplayContext.GAME -> showAllCards(Visibility.SHOW)
        DisplayContext.PLAYER -> showAllCards(Visibility.SHOW)
        DisplayContext.DEBUG -> showAllCards(Visibility.SHOW)
    }
    private fun showAllCards(visibilty: Visibility) =
            "cards=[" +cards.map{ it.CardDisplay().display(visibilty) } +"]"
}