package com.nilo.skipmo.lobby

internal class UserAuthentication {

    fun authenticateUser(user: User, password: String): User {
        if (isPasswordCorrect(user, password)) {
            logUserIn(user)
            return user
        } else {
            logUserOut(user)
            throw loginFailed()
        }
    }

    private fun logUserOut(user: User) { user.loggedIn = false }
    private fun logUserIn(user: User) { user.loggedIn = true}

    private fun isPasswordCorrect(user: User, password: String) = user.password == password
    private fun loginFailed() = Exception("User LOGIN failed")
}