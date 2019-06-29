package com.nilo.skipmo.lobby.facade

import com.nilo.skipmo.lobby.service.GuestUserService

internal class GuestLobby(private val guestUserService : GuestUserService) {

    fun logIn(name: String, password: String) = guestUserService.logIn(name, password)
    fun createUser(name: String, password: String) = guestUserService.createUser(name, password)

}