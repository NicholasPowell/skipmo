package com.nilo.skipmo.lobby.facade

import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryUserPersistence
import com.nilo.skipmo.lobby.scenarios.InMemoryLobby
import com.nilo.skipmo.lobby.scenarios.LobbyWithUsers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class LogInTests {

    internal class Scenario(
            var name : String = "NEW",
            var provider : LobbyWithUsers = LobbyWithUsers(),
            var persistence : ThrowAwayInMemoryUserPersistence = provider.inMemoryLobby.userPersistence )

    @Test
    fun newUserIsNotLoggedIn() {
        Assertions.assertFalse(InMemoryLobby().guestLobby.createUser("SUPER", "COOL").loggedIn)
    }

    @Test
    fun confirmUsersAreLoggedIn() {
        with(Scenario()) {
            logAllUsersIn()
            assertAllUsersAreLoggedIn()
        }
    }

    fun Scenario.logAllUsersIn() {
        provider.logAllIn()
    }

    fun Scenario.assertAllUsersAreLoggedIn() {
        persistence.users.values.forEach { Assertions.assertTrue(it.loggedIn) }
    }

}