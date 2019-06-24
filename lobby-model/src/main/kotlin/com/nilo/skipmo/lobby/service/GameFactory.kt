package com.nilo.skipmo.lobby.service

import com.nilo.skipmo.lobby.Game
import com.nilo.skipmo.lobby.Invitation
import java.util.*

internal class GameFactory {
    fun createGame(invitation: Invitation) = Game(invitation.user1, invitation.user2, UUID.randomUUID().toString())
}
