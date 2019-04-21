package com.nilo.skipmo.domain.lobby

import org.springframework.data.annotation.Id
import java.util.*

//class
//class GameWithPlayers( val game: Game, val users : MutableList<UUID> )
data class User(val name: String, @Id val id: UUID, val games: List<UUID> )

class Lobby()
//        val authenticator: Authenticator,
//        val games: MutableMap<UUID, GameWithUsers> = mutableMapOf() ) {
//
//    val gameFactory = GameFactory()
//    val userFinder = UserFinder()
//    val gameFinder = GameFinder()
//
//    fun logIn(name: String, password: String): Boolean  {
//        return authenticator.authenticate(name, password)
//    }
//
//
//    inner class GameFactory {
//        fun createGame(users: List<User>): Game {
//            val game = Game(*users.map { it.name }.toTypedArray())
//            games[game.id] = GameWithUsers(users.map { it.id }, game)
//            return game
//        }
//        fun deleteGame(gameId: UUID) {
//
//        }
//    }
//
//    inner class UserFinder {
//        fun listUserIs(): List<UUID> {
//            return games.flatMap { it.value.userIds }.distinct()
//        }
//    }
//    inner class GameFinder {
//        fun findGame(gameId: UUID): Game? {
//            return games[gameId]?.game
//        }
//
//
//        fun findGames(userId: UUID): List<GameWithUsers> =
//                games.filter { it.value.userIds.contains(userId) }.values.toList()
//
//        fun listGames(): List<GameWithUsers> {
//            return games.map { it.value }
//        }
//
//    }
//}