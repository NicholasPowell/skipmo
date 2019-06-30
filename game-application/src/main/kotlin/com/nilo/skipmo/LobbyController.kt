package com.nilo.skipmo

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
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono


@Configuration
open class GameRouter {

    @Autowired
    lateinit var webClientBuilder: WebClient.Builder

    @Autowired
    lateinit var greetingHandler: GameHandler

//    @Autowired
//    lateinit var gameMaker: PublicDomainBridge

    @Bean
    open fun route(): RouterFunction<ServerResponse> {
        return router {
            accept(APPLICATION_JSON).nest {
                POST("/game").invoke(greetingHandler::createGame)
                GET("/games").invoke(greetingHandler::sayHi)
            }
        }
    }
}

@Component

class GameHandler() {
//    @Autowired val gameMaker: PublicDomainBridge) {
//    }

    fun sayHi(request: ServerRequest) : Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body( BodyInserters.fromObject("{ \"hello\" : \"world\" }") )
    }

    fun createGame(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(
                        request.bodyToMono(CreateGameRequest::class.java)
                                .map(CreateGameRequest::players)
                                .map { g -> CreateGameResponse(g) }
                        , CreateGameResponse::class.java
                ))
    }
}

data class CreateGameRequest(val players: MutableList<String>)
data class CreateGameResponse(val players: MutableList<String>)
