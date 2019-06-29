package com.nilo.skipmo.lobby.scenarios

import com.nilo.skipmo.lobby.User
import com.nilo.skipmo.lobby.facade.GuestLobby
import com.nilo.skipmo.lobby.service.GuestUserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class LobbyWithUsers(val inMemoryLobby : InMemoryLobby = InMemoryLobby(),
                              val guestLobby: GuestUserService = inMemoryLobby.guestLobby,
                              val userCredentials: TestUserCredentials = TestUserCredentials() ) {

    var users = userCredentials.users.map { guestLobby.createUser(it.first, it.second) }

    fun logIn(namePw: Pair<String,String>) : User {
        return guestLobby.logIn(namePw.first, namePw.second)
    }

    fun logAllIn(): List<User> {
        return userCredentials.users.map{ logIn(it) }
    }

    fun failLogIn() : User {
        return guestLobby.logIn("NO", "NO")
    }
}

internal class LobbyWithUsersTests {
    @Test fun usersExist() = LobbyWithUsers().users.isNotEmpty()
    @Test fun usersCanLogIn() {
        val scenario = LobbyWithUsers()
        scenario.userCredentials.users.forEach{
            assertEquals( it.first, scenario.logIn(it).name )
        }
    }
    @Test fun canOverride() {
        assertEquals("OTHER",
                LobbyWithUsers(
                        userCredentials = TestUserCredentials(
                                users = mutableListOf( Pair("OTHER", "USER") )
                        )
                ).users.first().name )
    }
    @Test fun canFailLogin() {
        assertThrows<Exception> {
            LobbyWithUsers().failLogIn()
        }
    }
}

