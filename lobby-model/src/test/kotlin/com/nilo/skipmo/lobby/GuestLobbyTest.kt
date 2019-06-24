package com.nilo.skipmo.lobby

import com.nilo.skipmo.lobby.facade.GuestLobby
import com.nilo.skipmo.lobby.scenarios.InMemoryLobby
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class GuestLobbyTest {

    inner class Scenario(val guestLobby: GuestLobby = createGuestLobby(),
                         val user: User = createUser(guestLobby),
                         val loggedInUser: User = loginValid(guestLobby) )

    @Test
    fun createUserThenLogin() {
        with(Scenario()) {
            assertNotNull(user.id)
            assertEquals("Nick", loggedInUser.name)
            assertEquals(user.id, loggedInUser.id)
        }
    }

    @Test
    fun failLogInUserNotFound() {
        assertThrows<NoSuchElementException> {
            Scenario(user = createUserWithoutPersisting())
        }
    }

    @Test
    fun badCredentials() {
        assertThrows<Exception> {
            loginInvalid( createGuestLobby() )
        }
    }

    private fun createUser(guestLobby: GuestLobby) = guestLobby.createUser("Nick", "passtest")
    private fun loginValid(guestLobby: GuestLobby) = guestLobby.logIn("Nick", "passtest")
    private fun loginInvalid(guestLobby: GuestLobby) = guestLobby.logIn("Nick", "invalidpassword")
    private fun createGuestLobby() = InMemoryLobby().guestLobby
    private fun createUserWithoutPersisting() = User("Nick", "12345", "12345", false)
}