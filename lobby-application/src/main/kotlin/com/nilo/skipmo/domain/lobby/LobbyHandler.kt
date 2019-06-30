package com.nilo.skipmo.domain.lobby

import com.nilo.skipmo.lobby.api.GuestLobbyApi
import com.nilo.skipmo.lobby.api.LobbyApi
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.net.URI
import java.util.*

class LobbyHandler {

    fun getUserDetails(request: ServerRequest): Mono<ServerResponse> {
        return respondOkJson().body(
                Mono.just(request.pathVariable("id"))
                        .map { LobbyApi.instance.findUserById(it) }
        )
    }

    fun invite(serverRequest: ServerRequest): Mono<ServerResponse> {
        val inviteRequest: Mono<InviteRequest> = serverRequest.bodyToMono(InviteRequest::class.java)

        val response = inviteRequest.map {
            Pair(it, LobbyApi.instance.createGameInvitation(
                    it.uid1,
                    it.uid2))
        }

        return response.flatMap {
            respondCreatedJson(URI(it.second.id))
                    .body(it.second.toMono())
        }
    }

    fun acceptInvite(request: ServerRequest): Mono<ServerResponse> {
        return respondOkJson().body(
                request.bodyToMono(AcceptInviteRequest::class.java)
                        .map { LobbyApi.instance.createGame(it.id) }
        )
    }

    fun declineInvite(request: ServerRequest): Mono<ServerResponse> {
        return respondOkJson().body(
                request.pathVariable("id").toMono()
                        .map { LobbyApi.instance.declineGameInvitation(it) }
        )
    }

    fun getAllUsers(request: ServerRequest): Mono<ServerResponse> {
        return respondOkJson()
                .body(Flux.fromIterable(LobbyApi.instance.findAllUsers()))
    }

    fun logIn(request: ServerRequest): Mono<ServerResponse> {
        return respondOkJson().body(
                request.bodyToMono(LoginRequest::class.java)
                        .map { GuestLobbyApi.instance.logIn(it.name, it.password) }
        )
    }

    fun createUser(request: ServerRequest): Mono<ServerResponse> {
        val lobby = GuestLobbyApi.instance
        return respondOkJson()
                .body(request
                        .bodyToMono(CreateUserRequest::class.java)
                        .map { lobby.createUser(it.name, it.password) }
                )
    }

    private fun respondOkJson() = ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
    private fun respondCreatedJson(uri: URI) = ServerResponse.created(uri).contentType(MediaType.APPLICATION_JSON)

}

//data class DeclineInviteRequest(val id: String)
data class AcceptInviteRequest(val id: String)

data class InviteRequest(val uid1: String, val uid2: String)
data class CreateUserRequest(var name: String, var password: String)
data class LoginRequest(var name: String, var password: String)
data class CreateGameResponse(val gameId: UUID)
