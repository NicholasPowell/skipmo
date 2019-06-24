package com.nilo.skipmo.lobby.facade

import com.nilo.skipmo.lobby.User
import com.nilo.skipmo.lobby.scenarios.LobbyWithUsers
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class InvitationTests {

    inner class Scenario(val lobby : LobbyWithUsers = LobbyWithUsers().apply{ logAllIn() },
                         var loggedInUser : User = lobby.users.first()) {

    }

    @Test
    fun preValidateUser() {
        with(Scenario()) {
            assertTrue(lobby.inMemoryLobby.userPersistence.users[loggedInUser.id]?.loggedIn ?: false)
        }
    }

    @Test
    fun createInvitation() {
        with(Scenario()) {
            loggedInUser
        }
    }



}