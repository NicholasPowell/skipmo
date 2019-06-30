package com.nilo.skipmo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
open class GameRouters {

    @Bean
    open fun gameRoutes() : RouterFunction<ServerResponse> = router {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/").invoke { serverRequest-> ServerResponse.ok().syncBody("Hi") }
        }
    }
}