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
    @JsonManagedReference
    fun withPlayer(id: String) : PlayerPort
}

interface PlayerPort {
    @JsonBackReference
    fun draw() : GamePort
    @JsonBackReference
    fun moveCardInHand(srcDest: SrcDest) : GamePort
    @JsonBackReference
    fun playFromDiscardToBuildPile(srcDest: SrcDest) : GamePort
    @JsonBackReference
    fun playFromHandToBuildPile(srcDest: SrcDest) : GamePort
    @JsonBackReference
    fun playFromStockPile(src: String) : GamePort
    @JsonBackReference
    fun discard(srcDest: SrcDest) : GamePort
}

class SrcDest(val src: String, val dest : String)


