package com.nilo.skipmo.domain.lobby

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import java.util.*

@SpringBootApplication
@RibbonClient(name = "skipmo")
@RestController
@EnableDiscoveryClient
open class LobbyApplication {
    @Bean
    @LoadBalanced
    open fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }

    @Bean
    open fun route(): RouterFunction<ServerResponse> {
        return router {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("/createGame").invoke(::createGame)
            }
        }
    }

    fun createGame(request: ServerRequest): Mono<ServerResponse> {

        println("whaaat")

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(
                        webClientBuilder().baseUrl("http://skipmo:5001").build()
                                .post()
                                .uri("/game")
                                .body(BodyInserters.fromObject(object {
                                    val players = mutableListOf("Nick", "Lori")
                                }))
                                .retrieve()
                                .bodyToMono(String::class.java)
                )
    }

}

data class CreateGameResponse(val gameId: UUID )

fun main(args: Array<String>) {
    runApplication<LobbyApplication>(*args)
}
