package com.nilo.skipmo.lobby.api

import com.nilo.skipmo.lobby.UserAuthentication
import com.nilo.skipmo.lobby.api.domain.PublicUser
import com.nilo.skipmo.lobby.facade.GuestLobby
import com.nilo.skipmo.lobby.persistence.InMemoryUserPersistence
import com.nilo.skipmo.lobby.service.UserService

class GuestLobbyApi private constructor() {

    companion object {
        var instance: GuestLobbyApi = GuestLobbyApi()
            private set
    }

    private val userPersistence = InMemoryUserPersistence()
    private val userAuthentication = UserAuthentication()
    private val guestLobby: GuestLobby = GuestLobby(
            userPersistence,
            userAuthentication,
            UserService(userPersistence, userAuthentication))

    fun logIn(name: String, password: String) = PublicUser(guestLobby.logIn(name, password))
    fun createUser(name: String, password: String) = PublicUser(guestLobby.createUser(name, password))

}