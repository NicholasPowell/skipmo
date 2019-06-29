package com.nilo.skipmo.domain.lobby.integration

import com.google.gson.GsonBuilder
import com.nilo.skipmo.lobby.api.domain.PublicGameInvitation
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LobbyIntegrationTests(@Autowired val client: WebTestClient) {

    inner class Scenario(val guestScenario: GuestScenario = GuestScenario(client),
                         val userScenario: UserScenario = UserScenario(client)
    )

    @Test
    fun hmm() {
        println(GsonBuilder().setPrettyPrinting().create().toJson(createInvitation()))
        println(GsonBuilder().setPrettyPrinting().create().toJson(createInvitation()))
        println(String(Scenario().guestScenario.getAllUsersRequest().returnResult().responseBody))
    }

    fun createInvitation() : PublicGameInvitation {
        lateinit var response: PublicGameInvitation
        with(Scenario()) {
            with(guestScenario) {
                val uid1 = getUserId(createUserRequest(UUID.randomUUID().toString(), "PASS"))
                val uid2 = getUserId(createUserRequest(UUID.randomUUID().toString(), "PASS"))
                with(userScenario) {
                    response = deserializeInvitation(getResponse(createInvitation(uid1, uid2)))
                }
                return response
            }
        }
    }

}