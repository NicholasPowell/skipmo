package com.nilo.skipmo.lobby.facade

import com.nilo.skipmo.lobby.scenarios.InMemoryLobby
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class LobbyUserSearchTests {

    inner class Scenario(
            val inMemoryLobby: InMemoryLobby = InMemoryLobby(),
            val name: String = UUID.randomUUID().toString()
    )

    @Test
    fun byName() {
        with(Scenario()) {
            val user = createUser()
            assertEquals(user, findUserByName(user.name))
        }
    }

    @Test
    fun byId() {
        with(Scenario()) {
            val user = createUser()
            assertEquals(user, findUserById(user.id))
        }
    }

    fun Scenario.createUser() = inMemoryLobby.guestLobby.createUser(name, "USER")

    fun Scenario.findUserById(id: String) = inMemoryLobby.lobby.findUserById(id)

    fun Scenario.findUserByName(name: String) = inMemoryLobby.lobby.findUserByName(name)
}