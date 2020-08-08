//package com.nilo.skipmo.adapter
//
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Assertions.assertNotEquals
//import org.junit.jupiter.api.Test
//
//class DtoConversionTests {
//
//    @Test
//    fun `game is the same after converting to dto and back`() {
//        val game = dealGame()
//        val dto = convertGame(game)
//        originalSameAfterConvertingBack(game, dto)
//    }
//
//    @Test
//    fun `taking action on game is reflected in dto`() {
//        val game = dealGame()
//        val dto = convertGame(game)
//        changingGameStateConvertsToChangedDTO(game, dto)
//    }
//
//    private fun dealGame() = Game("Nick", "Lori").also { it.deal() }
//    private fun convertGame(game: Game) = Complete.GameDTO(game)
//
//    private fun originalSameAfterConvertingBack(game: Game, dto: Complete.GameDTO) {
//        assertEquals(game, dto.convert())
//    }
//
//    private fun changingGameStateConvertsToChangedDTO(game: Game, dtoBeforeChange: Complete.GameDTO) {
//        makeAMoveInTheGame(game)
//        val dtoAfterMove = convertGame(game)
//        assertNotEquals(dtoBeforeChange, dtoAfterMove)
//        assertEquals(game, dtoAfterMove.convert())
//    }
//
//    private fun dtoBeforeChangeIsDifferentFromDtoAfterMove(dtoBeforeChange: Complete.GameDTO, dtoAfterMove: Complete.GameDTO) {
//
//    }
//
//    private fun makeAMoveInTheGame(game: Game) {
//        game.currentPlayer.discard(HandSlotIndex.ONE, DiscardPileIndex.FOUR)
//    }
//
//}