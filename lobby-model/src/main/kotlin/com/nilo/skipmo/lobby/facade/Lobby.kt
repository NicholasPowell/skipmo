package com.nilo.skipmo.lobby.facade

import com.nilo.skipmo.lobby.*
import com.nilo.skipmo.lobby.api.LobbyApi
import com.nilo.skipmo.lobby.service.GameFactory
import com.nilo.skipmo.lobby.service.GameService
import com.nilo.skipmo.lobby.service.UserSearch

internal class Lobby(
        gamePersistence: GamePersistence,
        userPersistence: UserPersistence,
        private val invitationPersistence: InvitationPersistence,
        private val formatUserNotFoundError: (String) -> String = LobbyApi.Companion::formatUserNotFoundError,
        private val formatMissingInvitationError: (String) -> String = LobbyApi.Companion::formatMissingInvitationError,
        private val gameFactory: GameFactory = GameFactory(),
        private val gameService: GameService = GameService(gamePersistence, invitationPersistence, gameFactory),
        private val userSearch: UserSearch = UserSearch(userPersistence)

) {

    fun findGames() = gameService.findGames()
    fun findGames(user: User) = gameService.findGames(user)
    fun createGame(invitation: Invitation) = gameService.createGame(invitation)
    fun createGame(id: String) = findInvitationById(id)
    fun createGameInvitation(user: User, user2: User) = gameService.createGameInvitation(user, user2)
    fun createGameInvitation(uid1: String, uid2: String) =
            gameService.createGameInvitation(
                    findUserById(uid1) ?: throw Exception(formatUserNotFoundError(uid1)),
                    findUserById(uid2) ?: throw Exception(formatUserNotFoundError(uid2)))
    fun findUserByName(name: String) = userSearch.findUser(UserSearch.SearchBy(name=name))
    fun findUserById(id: String) = userSearch.findUser(UserSearch.SearchBy(id=id))
    fun findUsersLoggedIn() = userSearch.findUsersLoggedIn()
    fun findAllUsers() = userSearch.findAllUsers()
    fun findInvitationById(id: String) = invitationPersistence.getInvitation(id) ?: throw Exception(formatMissingInvitationError(id))
    fun declineInvitationById(id: String) = invitationPersistence.deleteInvitation(id)



}

