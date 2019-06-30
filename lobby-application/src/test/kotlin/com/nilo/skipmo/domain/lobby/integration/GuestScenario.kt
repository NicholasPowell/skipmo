package com.nilo.skipmo.domain.lobby.integration

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nilo.skipmo.domain.lobby.CreateUserRequest
import com.nilo.skipmo.lobby.api.domain.PublicUser
import com.nilo.skipmo.lobby.api.endpoints.GuestEndpoints
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

class GuestScenario(val client: WebTestClient,
//                    val name: String = UUID.randomUUID().toString(),
//                    val pass: String = "always_the_same",
                    val gson: Gson = GsonBuilder().create()

) {

    fun getAllUsersRequest(): WebTestClient.BodyContentSpec =
            client
                    .get()
                    .uri(GuestEndpoints.USERS.uri)
                    .exchange()
                    .expectBody()


    fun logInRequest(name: String, pass: String): WebTestClient.BodyContentSpec =
            client
                    .post()
                    .uri(GuestEndpoints.LOGIN.uri)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .syncBody(CreateUserRequest(name, pass))
                    .exchange()
                    .expectStatus().is2xxSuccessful
                    .expectBody()
                    .jsonPath("\$.name").isEqualTo(name)
                    .jsonPath("\$.loggedIn").isEqualTo("true")

    fun createUserRequest(name: String, pass: String): WebTestClient.BodyContentSpec =
            client
                    .post()
                    .uri(GuestEndpoints.CREATEUSER.uri)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .syncBody(CreateUserRequest(name, pass))
                    .exchange()
                    .expectStatus().is2xxSuccessful
                    .expectBody()
                    .jsonPath("\$.name").isEqualTo(name)

    fun getUserId(spec: WebTestClient.BodyContentSpec) =
            gson.fromJson(getResponse(spec), PublicUser::class.java).id

    fun getResponse(spec: WebTestClient.BodyContentSpec) = String(spec.returnResult().responseBody)
}