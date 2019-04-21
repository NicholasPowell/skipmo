package com.nilo.skipmo.domain.lobby

interface LobbyAction
class Login : LobbyAction
class Logout : LobbyAction
class ViewGames : LobbyAction
class ViewUsers : LobbyAction
class JoinGame : LobbyAction

abstract class ActionsProvider {
    abstract fun getActions() : List<LobbyAction>
}

class GuestActionsProvider : ActionsProvider() {
    override fun getActions() = listOf(
            Login(),
            ViewGames() ) }

class UserActionsProvider : ActionsProvider() {
    override fun getActions() = listOf(
            Logout(),
            ViewGames(),
            ViewUsers(),
            JoinGame() )
}


