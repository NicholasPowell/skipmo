package com.nilo

import com.nilo.skipmo.Adapter
import org.springframework.stereotype.Service

@Service
class GameApi {

    fun createGame(p1: String, p2: String) = Adapter().createGame(p1,p2)

}