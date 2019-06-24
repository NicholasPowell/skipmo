package com.nilo.skipmo.domain.lobby

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
open class LobbyRouters(val handler: LobbyHandler = LobbyHandler()) {

    @Bean
    open fun guestRoute() : RouterFunction<ServerResponse> = router {
        accept(MediaType.APPLICATION_JSON).nest {
            path("/guest").nest {
                POST("/logIn").invoke(handler::logIn)
                POST("/createUser").invoke(handler::createUser)
                GET("/allUsers").invoke(handler::getAllUsers)
            }
        }
    }

    @Bean
    open fun userRoute() : RouterFunction<ServerResponse> = router {
        accept(MediaType.APPLICATION_JSON). nest {
            path("/user").nest {
                GET("/{id}").invoke(handler::userDetails)
                POST("/invite").invoke(handler::invite)
                POST("/acceptInvite").invoke(handler::acceptInvite)
            }
        }
    }
}