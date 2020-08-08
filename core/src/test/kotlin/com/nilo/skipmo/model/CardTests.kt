package com.nilo.skipmo.model

import com.nilo.skipmo.model.domain.game.Card
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CardTests {

    @Test
    fun outOfRange() {
        Assertions.assertThrows(AssertionError::class.java) { Card(-1) }
        Assertions.assertThrows(AssertionError::class.java) { Card(13) }
    }

    @Test
    fun skipMoChecks() {
        Assertions.assertTrue(Card(0).isSkipMo())
    }
}