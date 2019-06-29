package com.nilo.skipmo.lobby.service

import com.nilo.skipmo.lobby.*

internal class GameService(private val gamePersistence: GamePersistence,
                           private val invitationPersistence: InvitationPersistence,
                           private val gameFactory: GameFactory = GameFactory()) {

    fun findGames() : Collection<Game> {
        return gamePersistence.getGames()
    }

    fun findGames(user: User) : Collection<Game>{
        return gamePersistence.getGames( user )
    }

    fun createGame(invitation: Invitation) : Game {
        val game = gameFactory.createGame(invitation)
        gamePersistence.addGame(game)
        return game
    }

    fun createGameInvitation(user1: User, user2: User) =
            invitationPersistence.addInvitation(user1, user2)

    fun finishGame(id: String) {
        gamePersistence.games[id]?.state = Game.State.FINISHED
    }

}