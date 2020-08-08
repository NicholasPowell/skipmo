package com.nilo.skipmo.model.domain.game

data class Card(val number: Int) {
    init {
        assert(number in 0..12)
    }

    fun isSkipMo() = number == 0

}
