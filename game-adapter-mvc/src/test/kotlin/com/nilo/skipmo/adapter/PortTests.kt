package com.nilo.skipmo.adapter

import com.nilo.skipmo.Adapter
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PortTests {

    @Test
    fun portTest() {

        val goodGame = Adapter().createGame("Nick", "Lori")
                .withPlayer("Nick").draw()
                .withPlayer("Nick").discard(SrcDest("ONE", "ONE"))
                .withPlayer("Lori").draw()
                .withPlayer("Lori").discard(SrcDest("THREE", "ONE"))
        assertEquals( "Nick", goodGame.getPlayerName() )
    }

    @Test
    fun wrongPlayer() {
        val goodSoFar = Adapter().createGame("Nick", "Lori")
                .withPlayer("Nick").draw()
                .withPlayer("Nick").discard(SrcDest("ONE", "ONE"))
        assertThrows<Exception> {
            goodSoFar.withPlayer("Nick").draw()
        }
    }
}

