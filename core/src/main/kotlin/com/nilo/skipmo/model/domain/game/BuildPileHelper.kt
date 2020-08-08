package com.nilo.skipmo.model.domain.game

internal class BuildPileHelper(
        private val buildPile: BuildPile,
        private val addToTop: (Card) -> Unit) {
    fun withSize(num: Int): BuildPile {
        if (num > 0) {
            (1..num).forEach { addToTop(Card(it)) }
        }
        return buildPile
    }
}