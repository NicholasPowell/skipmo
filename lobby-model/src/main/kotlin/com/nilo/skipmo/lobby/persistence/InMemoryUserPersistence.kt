package com.nilo.skipmo.lobby.persistence

import com.nilo.skipmo.lobby.User
import com.nilo.skipmo.lobby.UserPersistence
import java.util.*

// Worry about persistence later - trying to start with a solid model before
// filling in dependencies
internal class InMemoryUserPersistence : UserPersistence {

    companion object {
        val users = HashMap<String, User>()
    }

    override fun findAllLoggedInUsers() : List<User> = users.values.filter{ it.loggedIn }
    override fun findAllUsers() : List<User> = users.values.map{it}
    override fun findUserByName(name: String) = users.values.first { name == it.name }
    override fun findUserById(id: String) = users.values.first{ id == it.id}
    override fun getUser(userId: String) : User {
        val user = users[userId]
        if (user != null) {
            return user
        } else {
            throw notFound()
        }
    }
    override fun createUser(userName: String, password: String) : User {
        val user = User(userName, UUID.randomUUID().toString(), password)
        users[user.id] = user
        return user
    }

    override fun notFound() = Exception("User not found")

}
