package com.nilo.skipmo.domain.game

internal class PlayerDisplay(private val player: Player) {
    fun display(displayContext: DisplayContext) = player.name
}