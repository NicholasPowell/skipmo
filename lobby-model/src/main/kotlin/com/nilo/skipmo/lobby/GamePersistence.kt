package com.nilo.skipmo.lobby

internal interface GamePersistence {

    val games: MutableMap<String,Game>
    fun getGames() : Collection<Game> = games.values
    fun getGames( user : User ) : Collection<Game> = games.values.filter{ it.player1 == user || it.player2 == user}
    fun addGame(game: Game) {
        games[game.id] = game
    }
    fun saveGame(game: Game){
        games[game.id] = game
    }

}
