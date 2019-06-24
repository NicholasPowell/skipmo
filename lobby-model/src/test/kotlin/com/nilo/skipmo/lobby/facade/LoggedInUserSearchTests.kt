package com.nilo.skipmo.lobby.facade

import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryUserPersistence
import com.nilo.skipmo.lobby.scenarios.LobbyWithUsers
import com.nilo.skipmo.lobby.service.UserSearch
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LoggedInUserSearchTests {

    internal class Scenario(
            var name : String = "NEW",
            var provider : LobbyWithUsers = LobbyWithUsers(),
            var persistence : ThrowAwayInMemoryUserPersistence = provider.inMemoryLobby.userPersistence )

    @Test
    fun allButTheNewGuyLoggedIn() {
        with(Scenario( provider = givenLobbyWithLoggedInUsers() )) {
            createNewUserFromGuestLobby()
            newUserIsNotLoggedIn()
            loggedInSearchDoesNotFindNewUser()
        }
    }

    @Test
    fun logInANewGuyAndMakeSureHesFound() {
        with(Scenario( name = "NEW2", provider = givenLobbyWithLoggedInUsers() ) ) {
            createUser()
            logInUser()
            newUserIsLoggedIn()
            loggedInSearchFindsNewUser()
        }


    }

    fun Scenario.logInUser() {
        provider.guestLobby.logIn(name, "USER")
    }

    fun Scenario.createUser() {
        provider.guestLobby.createUser(name, "USER")
    }

    fun Scenario.newUserIsLoggedIn() {
        assertTrue(persistence.users.values.first { it.name == name }.loggedIn)
    }

    fun Scenario.loggedInSearchFindsNewUser() {
        assertEquals(persistence.users.size, UserSearch(persistence).findUsersLoggedIn().size)
    }

    fun Scenario.createNewUserFromGuestLobby() {
        provider.guestLobby.createUser(name, "whatever")
    }

    fun Scenario.newUserIsNotLoggedIn() {
        assertFalse(persistence.users.values.first { it.name == name }.loggedIn)
    }

    fun Scenario.loggedInSearchDoesNotFindNewUser() {
        assertEquals(persistence.users.size, UserSearch(persistence).findUsersLoggedIn().size + 1)
    }

    private fun givenLobbyWithLoggedInUsers() = LobbyWithUsers().also { it.logAllIn() }

}