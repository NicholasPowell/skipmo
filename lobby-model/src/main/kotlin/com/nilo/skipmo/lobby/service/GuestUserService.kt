package com.nilo.skipmo.lobby.service

import com.nilo.skipmo.lobby.User
import com.nilo.skipmo.lobby.UserAuthentication
import com.nilo.skipmo.lobby.UserPersistence
import com.nilo.skipmo.lobby.api.GuestLobbyApi

internal class GuestUserService(private val userPersistence : UserPersistence,
                                private val userAuthentication : UserAuthentication = UserAuthentication(),
                                private val formatUserNotFound: (String) -> String = GuestLobbyApi.Companion::formatUserNotFound,
                                private val formatNameAlreadyExists: (String) -> String = GuestLobbyApi.Companion::formatNameAlreadyExists
                           ) {

    fun logIn(name: String, password: String) =
            userAuthentication.authenticateUser(
                    userPersistence.findUserByName(name)
                            ?: throw Exception(formatUserNotFound(name)), password)

    fun createUser(name: String, password: String) : User {
        if (userPersistence.findUserByName(name) != null) {
            throw Exception(formatNameAlreadyExists(name))
        } else {
            return userPersistence.createUser(name, password)
        }
    }



}