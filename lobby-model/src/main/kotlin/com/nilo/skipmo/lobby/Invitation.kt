package com.nilo.skipmo.lobby

internal class Invitation(val id: String,
                          val user1: User,
                          val user2: User,
                          var accepted : Boolean = false)