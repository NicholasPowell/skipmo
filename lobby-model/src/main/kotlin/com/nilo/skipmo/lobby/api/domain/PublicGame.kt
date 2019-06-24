package com.nilo.skipmo.lobby.api.domain

import com.nilo.skipmo.lobby.Game
import java.time.LocalDate

class PublicGame(val player1: PublicUser,
                 val player2: PublicUser,
                 val id: String,
                 val created: LocalDate,
                 val state : String,
                 val url : String ) {
    internal constructor(game: Game) :
            this(PublicUser(game.player1),
                    PublicUser(game.player2),
                    game.id,
                    game.created,
                    game.state.toString(),
                    game.url )
}