package com.nilo.skipmo.lobby.api.domain

import com.nilo.skipmo.lobby.Invitation

class PublicGameInvitation(
        val id: String,
        val user1: PublicUser,
        val user2: PublicUser,
        var accepted : Boolean = false ) {
    internal constructor(invitation: Invitation) :
            this(   invitation.id,
                    PublicUser(invitation.user1),
                    PublicUser(invitation.user2),
                    invitation.accepted )
}