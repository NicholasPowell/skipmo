package com.nilo.skipmo.lobby

import com.nilo.skipmo.lobby.facade.Lobby
import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryGamePersistence
import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryInvitationPersistence
import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryUserPersistence
import com.nilo.skipmo.lobby.scenarios.LobbyWithUsers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LobbyFinderTests {

    inner class Scenario(
            val lobby: Lobby = Lobby(ThrowAwayInMemoryGamePersistence(), ThrowAwayInMemoryUserPersistence(), ThrowAwayInMemoryInvitationPersistence()),
            val userLobby: LobbyWithUsers = LobbyWithUsers(),
            val u1: User = userLobby.guestLobby.createUser("FIRST","FIRST"),
            val u2: User = userLobby.guestLobby.createUser("SECOND", "SECOND"),
            val invite: Invitation = lobby.createGameInvitation(u1,u2))

    @Test
    fun findGame() {
        with(Scenario()) {
            val game = createGame()
            assertGameFoundFor(u1, game)
            assertGameFoundFor(u2, game)
        }
    }

    @Test
    fun gameNotFound() {
        with(Scenario()) {
            noGamesFoundFor(u1)
            noGamesFoundFor(u2)
        }
    }
    fun Scenario.createGame() = lobby.createGame(invite)

    fun Scenario.noGamesFoundFor(user: User) {
        assertEquals(0, lobby.findGames(user).size)
    }

    fun Scenario.assertGameFoundFor(user: User, game: Game) {
        assertEquals(game, lobby.findGames(user).first())
    }
}