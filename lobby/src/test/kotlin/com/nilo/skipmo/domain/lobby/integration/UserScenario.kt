package com.nilo.skipmo.domain.lobby.integration

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nilo.skipmo.domain.lobby.AcceptInviteRequest
import com.nilo.skipmo.domain.lobby.InviteRequest
import com.nilo.skipmo.lobby.api.LobbyApi
import com.nilo.skipmo.lobby.api.domain.PublicGameInvitation
import com.nilo.skipmo.lobby.api.endpoints.UserEndpoints
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

class UserScenario(val client: WebTestClient,
                   val gson: Gson = GsonBuilder().create()) {

    companion object {
        var authToken : String? = null
    }

    fun registerJwt() {
        authToken = authToken ?: createJwt()
    }
    fun resetJwt() {
        authToken = null
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class TokenResponse( val access_token : String)

    class JwtRequest( val client_id : String,
                      val client_secret : String,
                      val audience : String,
                      val grant_type : String )
    private fun createJwt() : String {
        println("Pinging Auth0 for new JWT Token")
        return client
                .post()
                .uri("https://dev-7u2zxlph.auth0.com/oauth/token")
                .header("content-type", "application/json")
                .syncBody(
                        jacksonObjectMapper().writeValueAsString(
                        JwtRequest(
                                System.getenv("AUTH0_CLIENT_ID"),
                                System.getenv("AUTH0_CLIENT_SECRET"),
                                "https://skipmo.auth.nilo.com",
                                "client_credentials")
                        )
                )
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .returnResult()
                .responseBody
                .let { String(it) }
                .let { jacksonObjectMapper().readValue(it, TokenResponse::class.java) }
                .let { it.access_token }
                .let { "Bearer $it"}
    }

    fun createInvitation(uid1: String, uid2: String) : WebTestClient.BodyContentSpec {
        return client
                .post()
                .uri(UserEndpoints.CREATEINVITATION.uri)
                .header("Authorization", authToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(InviteRequest(uid1, uid2))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
    }

    fun acceptInvitation(id: String, status: HttpStatus = HttpStatus.OK) : WebTestClient.BodyContentSpec {
        return client
                .put()
                .uri(UserEndpoints.ACCEPTINVITATION.uri, mapOf("id" to id))
                .header("Authorization", authToken)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(AcceptInviteRequest(id))
                .exchange()
                .expectStatus().isEqualTo(status)
                .expectBody()
    }



    fun declineInvitation(id: String) :WebTestClient.BodyContentSpec {
        return client
                .delete()
                .uri(UserEndpoints.DECLINEINVIATION.uri, mapOf("id" to id))
                .header("Authorization", authToken)

//                .headerForTestToken()
                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .syncBody(DeclineInviteRequest(id))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
    }

    fun getResponse(spec: WebTestClient.BodyContentSpec) =
            String(spec.returnResult().responseBody)

    fun deserializeInvitation(response: String) =
            gson.fromJson(response, PublicGameInvitation::class.java)

    fun createInvitationNotFoundError(id: String) =LobbyApi.instance.createMissingInvitationError(id)

}