package com.nilo.skipmo.lobby.api

import com.nilo.skipmo.lobby.api.domain.PublicGame
import com.nilo.skipmo.lobby.api.domain.PublicGameInvitation
import com.nilo.skipmo.lobby.api.domain.PublicUser
import com.nilo.skipmo.lobby.facade.Lobby
import com.nilo.skipmo.lobby.persistence.InMemoryGamePersistence
import com.nilo.skipmo.lobby.persistence.InMemoryInvitationPersistence
import com.nilo.skipmo.lobby.persistence.InMemoryUserPersistence

class LobbyApi private constructor() {

    companion object {
        private val lobby: Lobby = Lobby(
                InMemoryGamePersistence(),
                InMemoryUserPersistence(),
                InMemoryInvitationPersistence(),
                ::formatUserNotFoundError)
        val instance = LobbyApi()

        fun formatUserNotFoundError(uid: String) = "User not found for uid=$uid"
    }

    fun createGame(id: String) =
            PublicGame(lobby.createGame(lobby.findInvitationById(id) ?: throw Exception(createMissingInvitationError(id))) )

    fun createMissingInvitationError(id: String) = "Could not find Invitation for $id"

    fun createGameInvitation(uid1: String, uid2: String) =
            PublicGameInvitation(lobby.createGameInvitation(uid1, uid2))

    fun declineGameInvitation(id: String) {
        lobby.declineInvitationById(id)
    }

    fun findAllUsers() = lobby.findAllUsers().map{ PublicUser(it) }

    fun findUserById(id: String) = PublicUser(lobby.findUserById(id) ?: throw Exception(formatUserNotFoundError(id)))

}

