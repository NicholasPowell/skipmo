package com.nilo.skipmo.model.actions

import com.nilo.skipmo.model.domain.game.Player

internal interface AppliesToPlayer {
    fun applyTo(player: Player)
}