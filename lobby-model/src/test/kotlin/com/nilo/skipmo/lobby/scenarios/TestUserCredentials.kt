package com.nilo.skipmo.lobby.scenarios

import java.util.*

internal class TestUserCredentials(
        val users : MutableList<Pair<String, String>> = mutableListOf(
                Pair("Nick", "Pass"),
                Pair("Lori", "Rocks"),
                Pair("David", "Cute")
        )
) {
    fun addRandomCredentials() : Pair<String, String>{
        val pair = Pair(createRandomString(), createRandomString())
        users += pair
        return pair
    }
    private fun createRandomString() = UUID.randomUUID().toString()
}