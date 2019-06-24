package com.nilo.skipmo.lobby.service

import com.nilo.skipmo.lobby.UserAuthentication
import com.nilo.skipmo.lobby.UserPersistence

internal class UserService(private val userPersistence : UserPersistence,
                           private val userAuthentication : UserAuthentication = UserAuthentication()) {

    fun logIn(userName: String, password: String) =
            userAuthentication.authenticateUser(
                    userPersistence.findUserByName(userName), password)

    fun createUser(name: String, password: String) = userPersistence.createUser(name, password)

}