package com.nilo

import com.nilo.skipmo.CoreAdapter
import org.springframework.stereotype.Service

@Service
class GameApi {

    fun createGame(p1: String, p2: String) = CoreAdapter().createGame(p1,p2)

}