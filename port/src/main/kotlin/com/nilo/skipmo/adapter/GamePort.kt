package com.nilo.skipmo.adapter

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference

interface Binding{
    fun getGameState()
    fun handleWinner()
    fun getPort() : Port
}

interface Port {
    fun createGame(p1: String, p2: String) : GamePort
}
interface GamePort {
    fun getPlayerName() : String
    fun withPlayer(id: String) : PlayerPort
    fun getDisplay() : Complete.GameDTO
}




interface PlayerPort {
    fun draw() : GamePort
    fun moveCardInHand(srcDest: SrcDest) : GamePort
    fun playFromDiscardToBuildPile(srcDest: SrcDest) : GamePort
    fun playFromHandToBuildPile(srcDest: SrcDest) : GamePort
    fun playFromStockPile(src: String) : GamePort
    fun discard(srcDest: SrcDest) : GamePort
}

class SrcDest(val src: String, val dest : String)


