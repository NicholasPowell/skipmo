package com.nilo.skipmo.lobby.api

import com.nilo.skipmo.lobby.UserAuthentication
import com.nilo.skipmo.lobby.api.domain.PublicUser
import com.nilo.skipmo.lobby.persistence.InMemoryUserPersistence
import com.nilo.skipmo.lobby.service.GuestUserService

class GuestLobbyApi private constructor() {

    companion object {
        var instance: GuestLobbyApi = GuestLobbyApi()
            private set
        fun formatUserNotFound(name : String) = "User name $name not found"
        fun formatNameAlreadyExists(name: String) = "User name $name already exists, please choose another"
    }

    private val userPersistence = InMemoryUserPersistence()
    private val userAuthentication = UserAuthentication()
    private val guestUserService: GuestUserService = GuestUserService(userPersistence, userAuthentication)

    fun logIn(name: String, password: String) = PublicUser(guestUserService.logIn(name, password))
    fun createUser(name: String, password: String) = PublicUser(guestUserService.createUser(name, password))

}