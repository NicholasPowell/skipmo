package com.nilo.skipmo.domain.lobby

import com.google.gson.GsonBuilder
import com.nilo.skipmo.lobby.api.GuestLobbyApi
import com.nilo.skipmo.lobby.api.LobbyApi
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

class LobbyHandler {

    fun userDetails(request: ServerRequest) : Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body( Mono.just(request.pathVariable("id"))
                        .map { LobbyApi.instance.findUserById(it) }
                )
    }

    fun invite(request: ServerRequest) : Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(
                        request.bodyToMono(InviteRequest::class.java)
                                .map {
                                    LobbyApi.instance.createGameInvitation(it.uid1, it.uid2)
                                }
                )
    }

    fun acceptInvite(request: ServerRequest) : Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(request.bodyToMono(AcceptInviteRequest::class.java)
                                .map { LobbyApi.instance.createGame(it.id) }
                )
    }

    fun getAllUsers(request: ServerRequest) : Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(
                        Flux.fromIterable(LobbyApi.instance.findAllUsers())
                )
    }

    fun logIn(request: ServerRequest) : Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(request
                        .bodyToMono(LoginRequest::class.java)
                        .map{ GuestLobbyApi.instance
                                .logIn( it.userName, it.password) }
                )
    }

    fun createUser(request: ServerRequest): Mono<ServerResponse> {
        val lobby = GuestLobbyApi.instance
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(
                        request.bodyToMono( CreateUserRequest::class.java ).map{
                            lobby.createUser(it.userName, it.password)
                        }
                )
    }
}

data class AcceptInviteRequest(val id: String)
data class InviteRequest(val uid1 : String, val uid2: String)
data class CreateUserRequest(var userName: String, var password: String)
data class LoginRequest(var userName: String, var password: String)
data class CreateGameResponse(val gameId: UUID)
