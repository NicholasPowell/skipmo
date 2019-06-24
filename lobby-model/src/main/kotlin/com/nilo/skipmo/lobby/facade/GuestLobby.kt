package com.nilo.skipmo.lobby.facade

import com.nilo.skipmo.lobby.UserAuthentication
import com.nilo.skipmo.lobby.UserPersistence
import com.nilo.skipmo.lobby.service.UserService

internal class GuestLobby(private val userPersistence : UserPersistence,
                          private val userAuthentication : UserAuthentication = UserAuthentication(),
                          private val userService : UserService = UserService(userPersistence, userAuthentication)) {

    fun logIn(name: String, password: String) = userService.logIn(name, password)
    fun createUser(name: String, password: String) = userService.createUser(name, password)

}