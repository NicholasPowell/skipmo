package com.nilo.skipmo.domain.game

import com.nilo.skipmo.domain.public.Card

internal class Card(val number: Int) {
    init { assert( number in 0..12 ) }
    fun isSkipMo() = number == 0

    inner class CardDtoConverter{
        fun convert() = Card(number)
    }

    inner class CardDisplay{
        fun display(displayContext: DisplayContext) =display(Visibility.SHOW)
        fun display(visibility: Visibility) = when(visibility) {
                Visibility.SHOW -> if (number > 0) number.toString() else  "Skip Mo "
                Visibility.HIDE -> "Card"
            }
    }
}
