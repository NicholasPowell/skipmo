package com.nilo.skipmo.lobby.facade

import com.nilo.skipmo.lobby.*
import com.nilo.skipmo.lobby.GamePersistence
import com.nilo.skipmo.lobby.Invitation
import com.nilo.skipmo.lobby.InvitationPersistence
import com.nilo.skipmo.lobby.User
import com.nilo.skipmo.lobby.UserPersistence
import com.nilo.skipmo.lobby.service.GameFactory
import com.nilo.skipmo.lobby.service.GameService
import com.nilo.skipmo.lobby.service.UserSearch

internal class Lobby(
        private val gamePersistence: GamePersistence,
        private val userPersistence: UserPersistence,
        private val invitationPersistence: InvitationPersistence,
        private val gameFactory: GameFactory = GameFactory(),
        private val gameService: GameService = GameService(gamePersistence, invitationPersistence, gameFactory),
        private val userSearch: UserSearch = UserSearch(userPersistence)) {

    fun findGames() = gameService.findGames()
    fun findGames(user: User) = gameService.findGames(user)
    fun createGame(invitation: Invitation) = gameService.createGame(invitation)
    fun createGame(id: String) : Game {
        val invitation = findInvitationById(id)
        if( invitation != null ) {
            return createGame(invitation)
        } else {
            throw Exception("Invitation not found")
        }
    }
    fun createGameInvitation(user: User, user2: User) = gameService.createGameInvitation(user, user2)
    fun createGameInvitation(uid1: String, uid2: String) =
            gameService.createGameInvitation(findUserById(uid1), findUserById(uid2))
    fun findUserByName(name: String) = userSearch.findUser(UserSearch.SearchBy(name=name))
    fun findUserById(id: String) = userSearch.findUser(UserSearch.SearchBy(id=id))
    fun findUsersLoggedIn() = userSearch.findUsersLoggedIn()
    fun findAllUsers() = userSearch.findAllUsers()
    fun findInvitationById(id: String) = invitationPersistence.getInvitation(id)


}
