package com.nilo.skipmo

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping("/game")
class GameController {

    var ids = 1L

    var uuid: UUID = UUID.randomUUID()

    @GetMapping("/fax")
    fun hmm() = Flux.just("hello", "world")

//    @GetMapping( "/display/{gameId}/{playerId}" )
//    fun display(
//            @PathVariable("gameId") gameId : String,
//            @PathVariable("playerId") playerId : String
//            ) = Mono.just("$gameId:$playerId")


}