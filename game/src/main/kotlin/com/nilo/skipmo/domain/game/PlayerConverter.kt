package com.nilo.skipmo.domain.game

internal class PlayerConverter(private val player: Player) {
    fun toDto() = com.nilo.skipmo.domain.public.Player(player.name, player.hand.toDto(), player.stockPile.toDto(), player.discardPiles.map { it.toDto() })
}