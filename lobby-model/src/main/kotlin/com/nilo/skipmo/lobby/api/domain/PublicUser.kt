package com.nilo.skipmo.lobby.api.domain

import com.nilo.skipmo.lobby.User

class PublicUser(val name: String,
                 val id: String,
                 val loggedIn: Boolean) {
    internal constructor(user: User) :
            this(   user.name,
                    user.id,
                    user.loggedIn )
}