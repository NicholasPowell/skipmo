package com.nilo.skipmo.lobby

import com.nilo.skipmo.lobby.facade.GuestLobby
import com.nilo.skipmo.lobby.facade.Lobby
import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryGamePersistence
import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryInvitationPersistence
import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryUserPersistence
import com.nilo.skipmo.lobby.scenarios.LobbyWithUsers
import com.nilo.skipmo.lobby.service.UserSearch
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class UserLobbyTest {

    inner class Scenario(val persistence: ThrowAwayInMemoryUserPersistence = ThrowAwayInMemoryUserPersistence(),
                         val guestLobby: GuestLobby = GuestLobby(persistence),
                         val p1: User = guestLobby.createUser("player1", "password"),
                         val p2: User = guestLobby.createUser("player2", "password"),
                         val gameLobby: Lobby = Lobby(ThrowAwayInMemoryGamePersistence(), ThrowAwayInMemoryUserPersistence(), ThrowAwayInMemoryInvitationPersistence()),
                         val userLobby: UserSearch = UserSearch(persistence),
                         val foundUser1: User = userLobby.findAllUsers().first{ it.name == "player1"},
                         val foundUser2: User = userLobby.findAllUsers().first{ it.name == "player2"})

    @Test
    fun userWithNoGames() {
        val user = getValidUser()
        assertEquals( 0, Lobby(ThrowAwayInMemoryGamePersistence(), ThrowAwayInMemoryUserPersistence(), ThrowAwayInMemoryInvitationPersistence()).findGames().size )
    }

    @Test
    fun createInvitation() {
        with(Scenario()) {
            val invitation = gameLobby.createGameInvitation(foundUser1, foundUser2)
            assertEquals(foundUser1.id, invitation.user1.id)
            assertEquals(foundUser2.id, invitation.user2.id)
            gameLobby.createGame(invitation.id)

        }
    }

    private fun getValidUser() = LobbyWithUsers().logAllIn().first()

}