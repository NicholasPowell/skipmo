package com.nilo.skipmo.lobby

import com.nilo.skipmo.lobby.facade.GuestLobby
import com.nilo.skipmo.lobby.persistence.ThrowAwayInMemoryUserPersistence
import com.nilo.skipmo.lobby.service.UserSearch
import org.junit.jupiter.api.Test

internal class LobbyHappyPath {


    @Test
    fun happiness()  {
        val userPersistence = ThrowAwayInMemoryUserPersistence()
        val guestLobby = GuestLobby(userPersistence)

        var user1 = guestLobby.createUser("User1", "pass")
        var user2 = guestLobby.createUser("User2", "pass")

        val userLobby1 = UserSearch(userPersistence)
        val userLobby2 = UserSearch(userPersistence)

//        userLobby1.createGame()

    }

}