package com.nilo.skipmo.lobby.api

import com.nilo.skipmo.lobby.InvitationPersistence
import com.nilo.skipmo.lobby.api.domain.PublicGame
import com.nilo.skipmo.lobby.api.domain.PublicGameInvitation
import com.nilo.skipmo.lobby.api.domain.PublicUser
import com.nilo.skipmo.lobby.facade.Lobby
import com.nilo.skipmo.lobby.persistence.InMemoryGamePersistence
import com.nilo.skipmo.lobby.persistence.InMemoryInvitationPersistence
import com.nilo.skipmo.lobby.persistence.InMemoryUserPersistence

class LobbyApi private constructor() {

    companion object {
        private val lobby: Lobby = Lobby(InMemoryGamePersistence(), InMemoryUserPersistence(), InMemoryInvitationPersistence())
        val instance = LobbyApi()
    }

    fun findGames() = lobby.findGames().map{ PublicGame(it) }
    fun createGameInvitation(uid1: String, uid2: String) =
            PublicGameInvitation(lobby.createGameInvitation(uid1, uid2))
//    fun findGames(user: PublicUser) = gameService.findGames(user)
    fun createGame(invitationId: String) : PublicGame  {
        return PublicGame(lobby.createGame(invitationId))
    }
    //    fun findUserByName(name: String) = userSearch.findUser(UserSearch.SearchBy(name=name))
    fun findUserById(id: String) = PublicUser(lobby.findUserById(id))
    //    fun findUsersLoggedIn() = userSearch.findUsersLoggedIn()
    fun findAllUsers() = lobby.findAllUsers().map{ PublicUser(it) }
}

