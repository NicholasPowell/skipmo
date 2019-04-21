package com.nilo.skipmo

import com.nilo.skipmo.domain.Game
import com.nilo.skipmo.domain.GameMaker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import java.util.*


@Configuration
open class GameRouter {

    @Autowired
    lateinit var webClientBuilder: WebClient.Builder

    @Autowired
    lateinit var greetingHandler: GameHandler


    @Bean
    open fun route(): RouterFunction<ServerResponse> {
        return router {
            accept(APPLICATION_JSON).nest {
                POST("/game").invoke(greetingHandler::createGame)
            }
        }
    }
}


@Component
class GameHandler(@Autowired val gameMaker: GameMaker) {

    fun createGame(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(
                        request.bodyToMono(CreateGameRequest::class.java)
                                .map(CreateGameRequest::players)
                                .map(gameMaker::makeMeAGame)
                                .map { g -> CreateGameResponse(g.id, g) }
                        , CreateGameResponse::class.java
                ))
    }
}

data class CreateGameRequest(val players: MutableList<String>)
data class CreateGameResponse(val gameId: UUID, var game: Game)
