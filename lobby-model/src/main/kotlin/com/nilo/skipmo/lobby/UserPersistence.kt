package com.nilo.skipmo.lobby

internal interface UserPersistence {
    fun findAllLoggedInUsers() : List<User>
    fun findAllUsers() : List<User>
    fun findUserByName(name: String): User
    fun findUserById(id: String) : User
    fun getUser(userId: String) : User
    fun createUser(userName: String, password: String) : User
    fun notFound(): Exception
}