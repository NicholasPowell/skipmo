package com.nilo

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nilo.skipmo.adapter.SrcDest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class GameController(val gameApi: GameApi) {

    val mapper = jacksonObjectMapper()
            .writerWithDefaultPrettyPrinter()


    @GetMapping
    fun hello() : String {
        val game = gameApi.createGame("Nick", "Lori")
        println(game.getPlayerName())
        game.withPlayer("Nick")
                .draw()
//                .discard(SrcDest("ONE", "ONE"))

        return mapper.writeValueAsString(game.getPlayerName())

    }

}