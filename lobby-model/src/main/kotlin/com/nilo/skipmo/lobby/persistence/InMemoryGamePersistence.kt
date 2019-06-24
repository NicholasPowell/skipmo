package com.nilo.skipmo.lobby.persistence

import com.nilo.skipmo.lobby.Game
import com.nilo.skipmo.lobby.GamePersistence

internal class InMemoryGamePersistence(override val games: MutableMap<String, Game> = sharedGames) : GamePersistence {
    companion object {
        val sharedGames: MutableMap<String, Game> = mutableMapOf()
    }
}