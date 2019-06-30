package com.nilo.skipmo.domain.lobby

import com.nilo.skipmo.GameRouter
import org.junit.FixMethodOrder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runners.MethodSorters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ExtendWith(SpringExtension::class)
class LobbyControllerTest {


    @Autowired
    lateinit var gameRouter: GameRouter

    @Test
    fun wcSetup() {
//        val wtc = WebTestClient.bindToRouterFunction(gameRouter.route()).build()

//        wtc
//                .get().uri("/hmm")
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)

    }
//    @GetMapping("/lobby/{*e}")
//    fun resolveAction(@PathVariable(name = "e") e: String): Mono<String> {
//        println("just ok")
//        return Mono.just("hi")
//    }
}

