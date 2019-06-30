package com.nilo.skipmo.domain.lobby

import com.nilo.skipmo.lobby.api.endpoints.GuestEndpoints
import com.nilo.skipmo.lobby.api.endpoints.UserEndpoints
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
open class LobbyRouters(val handler: LobbyHandler = LobbyHandler()) {

    @Bean
    open fun lobbyRoutes() : RouterFunction<ServerResponse> = router {
        accept(MediaType.APPLICATION_JSON).nest {
            GET(UserEndpoints.USERS.uri).invoke(handler::getAllUsers)
            GET(UserEndpoints.DETAILS.uri).invoke(handler::getUserDetails)
            POST(UserEndpoints.CREATEINVITATION.uri).invoke(handler::invite)
            PUT(UserEndpoints.ACCEPTINVITATION.uri).invoke(handler::acceptInvite)
            DELETE(UserEndpoints.DECLINEINVIATION.uri).invoke(handler::declineInvite)

            POST(GuestEndpoints.LOGIN.uri).invoke(handler::logIn)
            GET(GuestEndpoints.USERS.uri).invoke(handler::getAllUsers)
            POST(GuestEndpoints.CREATEUSER.uri).invoke(handler::createUser)
        }
    }
}