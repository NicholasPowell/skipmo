package com.nilo.skipmo.domain.lobby.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.nilo.skipmo.lobby.api.GuestLobbyApi
import com.nilo.skipmo.lobby.api.LobbyApi
import com.nilo.skipmo.lobby.api.domain.PublicGame
import com.nilo.skipmo.lobby.api.domain.PublicGameInvitation
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.Alphanumeric::class)
class BusinessTestCases(@Autowired val client: WebTestClient,
                        @Autowired val objectMapper: ObjectMapper) {

    inner class Scenario(val guestScenario: GuestScenario = GuestScenario(client),
                         val userScenario: UserScenario = UserScenario(client))

    @BeforeAll
    fun makeSureJwtIsRegistered() {
        Scenario().userScenario.registerJwt()
    }

    @Test
    fun `user call fails without valid jwt`() {
        with(Scenario().userScenario) {
            resetJwt()
            unauthorizedUserCallFailsWith401()
            registerJwt()
        }
    }

    @Test
    fun `guest logs in`() {
        with(Scenario()) {
            createUserAndLogin("Bob")
        }
    }

    @Test
    fun `guest views current users`() {
        with(Scenario().guestScenario) {
            createUser("Fred")
            getUsersFromGuestIncludesAUserWithName("Fred")
        }
    }

    @Test
    fun `user invites another to a game, which is accepted`() {
        with(Scenario()) {
            val (uid1,uid2) =   createAndLogInTwoUsers()
            val game =          inviteAndAcceptForNewGame(uid1, uid2)
            newGameHasBothPlayers(uid1, uid2, game)
        }
    }

    @Test
    fun `user invites another to a game, which is declined`() {
        with(Scenario()) {
            val (uid1, uid2) = createAndLogInTwoUsers()
            val invitation =   createInvitationRequest(uid1, uid2)
            declineInvitation(invitation.id)
            acceptInvitationThrowsNotFoundError(invitation)
        }
    }

    @Test
    fun `guest attempts to create a user with an existing name`() {
        with(Scenario().guestScenario) {
            createUser("Simon")
                    .run  { createAnotherUserWithSameNameThrowsAssertionError("Simon") }
                    .also { errorMessageContainsNameAlreadyExists(it, "Simon") }
        }
    }

    private fun UserScenario.unauthorizedUserCallFailsWith401() {
        assertThrows<AssertionError> {
            acceptInvitation("anystring")
        }.message.also { assertTrue(it!!.contains("Status expected:<200> but was:<401>")) }
    }

    private fun GuestScenario.createUser(name: String) =
            createUserRequest(name, "AnyPassword")

    private fun GuestScenario.createAnotherUserWithSameNameThrowsAssertionError(name: String) =
            assertThrows<AssertionError> { createUserRequest(name, "Whatever") }

    private fun errorMessageContainsNameAlreadyExists(error: AssertionError, name: String) {
        assertTrue(
                error.message!!.contains(
                        GuestLobbyApi.formatNameAlreadyExists("Simon"))
        )
    }
    private fun GuestScenario.getUsersFromGuestIncludesAUserWithName(name: String) {
        getAllUsersRequest().jsonPath("\$[?(@.name =~ /$name/)]").isNotEmpty
    }

    private fun Scenario.acceptInvitationThrowsNotFoundError(invitation: PublicGameInvitation) {
        acceptInvitation(invitation.id)
                .jsonPath("\$.message")
                .isEqualTo(LobbyApi.formatMissingInvitationError(invitation.id))
    }

    private fun Scenario.acceptInvitation(id: String) =
            userScenario.acceptInvitation(id, HttpStatus.INTERNAL_SERVER_ERROR)

    private fun Scenario.createAndLogInTwoUsers() =
            listOf( createUserAndLogin(UUID.randomUUID().toString()),
                    createUserAndLogin(UUID.randomUUID().toString()) )

    private fun Scenario.inviteAndAcceptForNewGame(uid1: String, uid2: String) : PublicGame {
        with(userScenario) {
            return createInvitationRequest(uid1, uid2)
                    .let { acceptInvitation(it.id) }
                    .let { getResponse(it) }
                    .let { deserializeGame(it) }
        }
    }
    private fun Scenario.createInvitationRequest(uid1: String, uid2: String) =
            with(userScenario) {
                createInvitation(uid1, uid2)
                        .let { getResponse(it) }
                        .let { deserializeInvitation(it) }
            }

    private fun Scenario.createUserAndLogin(name: String) : String {
        with(guestScenario) {
            return createUserRequest(name, "PASS")
                    .also{ logInRequest(name, "PASS") }
                    .let { getUserId(it) }
        }
    }

    private fun Scenario.declineInvitation(uid: String) {
        userScenario.declineInvitation(uid)
    }

    private fun newGameHasBothPlayers(uid1: String, uid2: String, game: PublicGame) {
        assertEquals(uid1, game.player1.id)
        assertEquals(uid2, game.player2.id)
    }

    private fun deserializeGame(json: String) = objectMapper.readValue(json, PublicGame::class.java)
}