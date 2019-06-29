package com.nilo.skipmo.lobby.scenarios

import com.nilo.skipmo.lobby.facade.GuestLobby
import com.nilo.skipmo.lobby.facade.Lobby
import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryGamePersistence
import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryInvitationPersistence
import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryUserPersistence
import com.nilo.skipmo.lobby.service.GameFactory
import com.nilo.skipmo.lobby.service.GameService
import com.nilo.skipmo.lobby.service.GuestUserService
import com.nilo.skipmo.lobby.service.UserSearch

internal class InMemoryLobby(
        val userPersistence: ThrowAwayInMemoryUserPersistence = ThrowAwayInMemoryUserPersistence(),
        val gamePersistence: ThrowAwayInMemoryGamePersistence = ThrowAwayInMemoryGamePersistence(),
        val invitationPersistence: ThrowAwayInMemoryInvitationPersistence = ThrowAwayInMemoryInvitationPersistence(),
        val guestLobby : GuestUserService = GuestUserService(userPersistence),
        val lobby : Lobby = Lobby(
                gamePersistence,
                userPersistence,
                invitationPersistence)
)
