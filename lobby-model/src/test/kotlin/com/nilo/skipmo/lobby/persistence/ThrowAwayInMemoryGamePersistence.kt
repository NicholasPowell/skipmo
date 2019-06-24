package com.nilo.skipmo.lobby.persistence

import com.nilo.skipmo.lobby.Game
import com.nilo.skipmo.lobby.GamePersistence

internal class ThrowAwayInMemoryGamePersistence(
        override val games: MutableMap<String, Game> = mutableMapOf()) : GamePersistence