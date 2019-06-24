package com.nilo.skipmo.domain.lobby.integration

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nilo.skipmo.domain.lobby.AcceptInviteRequest
import com.nilo.skipmo.domain.lobby.InviteRequest
import com.nilo.skipmo.lobby.api.domain.PublicGameInvitation
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

class UserScenario(val client: WebTestClient,
                   val gson: Gson = GsonBuilder().create(),
                   val userEndpoints: UserEndpoints = UserEndpoints() ) {

    fun createInvitation(uid1: String, uid2: String) : WebTestClient.BodyContentSpec {
        return client
                .post()
                .uri(userEndpoints.createInvitationUri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(InviteRequest(uid1, uid2))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
    }

    fun acceptInvitation(id: String) : WebTestClient.BodyContentSpec {
        return client
                .post()
                .uri(userEndpoints.acceptInvitationUri)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(AcceptInviteRequest(id))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
    }



    fun getResponse(spec: WebTestClient.BodyContentSpec) =
            String(spec.returnResult().responseBody)

    fun asGameInvitation(response: String) =
            gson.fromJson(response, PublicGameInvitation::class.java)

}