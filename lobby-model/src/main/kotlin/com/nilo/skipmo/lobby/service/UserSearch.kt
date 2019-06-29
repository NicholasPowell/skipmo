package com.nilo.skipmo.lobby.service

import com.nilo.skipmo.lobby.User
import com.nilo.skipmo.lobby.UserPersistence

internal class UserSearch(private val userPersistence : UserPersistence) {

    class SearchBy(val name : String? = null, val id : String? = null)

    fun findUser(searchBy: SearchBy) : User? {

        if( searchBy.id != null ) {
            return userPersistence.findUserById(searchBy.id)
        }
        else if( searchBy.name != null ) {
            return userPersistence.findUserByName(searchBy.name)
        }

        else throw Exception("User not found")
    }

    fun findAllUsers() : List<User> = userPersistence.findAllUsers()
    fun findUsersLoggedIn() = userPersistence.findAllLoggedInUsers()

}