package com.nilo.skipmo.lobby

import java.time.LocalDate

internal class Game(
        val player1: User,
        val player2: User,
        val id: String,
        val created: LocalDate = LocalDate.now(),
        var state : State = State.NEW,
        var url : String = ""
) {

    enum class State { NEW, IN_PROGRESS, FINISHED }

    fun create() = {}
    fun delete() = {}
    fun goTo() = {}

}

