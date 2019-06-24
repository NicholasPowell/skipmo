package com.nilo.skipmo.domain.lobby.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.GsonBuilder
import com.nilo.skipmo.lobby.api.domain.PublicGame
import com.nilo.skipmo.lobby.api.domain.PublicGameInvitation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BusinessTestCases(@Autowired val client: WebTestClient, @Autowired val objectMapper: ObjectMapper) {

    inner class Scenario(val guestScenario: GuestScenario = GuestScenario(client),
                         val userScenario: UserScenario = UserScenario(client)
    )

    @Test
    fun happyPath() {
        lateinit var uid1: String
        lateinit var uid2: String
        lateinit var invite: PublicGameInvitation
        with(Scenario()) {
            with(guestScenario) {
                uid1 = getId(createUserRequest("user1", "PASS"))
                uid2 = getId(createUserRequest("user2", "PASS"))

                logInRequest("user1", "PASS")
                logInRequest("user2", "PASS")
            }
            with(userScenario) {
                invite = asGameInvitation(getResponse(createInvitation(uid1, uid2)))

                val game = objectMapper.readValue(
                        getResponse(acceptInvitation(invite.id)),
                        PublicGame::class.java)

                assertEquals(uid1, game.player1.id)
                assertEquals(uid2, game.player2.id)

            }

        }
    }


}