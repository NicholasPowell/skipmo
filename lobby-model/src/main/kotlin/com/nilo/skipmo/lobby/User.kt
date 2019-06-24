package com.nilo.skipmo.lobby

internal class User(
        val name: String,
        val id: String,
        var password: String,
        var loggedIn: Boolean = false
        )