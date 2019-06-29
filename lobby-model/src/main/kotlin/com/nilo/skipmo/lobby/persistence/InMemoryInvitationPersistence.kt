package com.nilo.skipmo.lobby.persistence

import com.nilo.skipmo.lobby.Invitation
import com.nilo.skipmo.lobby.InvitationPersistence

internal class InMemoryInvitationPersistence(override val invitations: MutableMap<String, Invitation> = sharedInvitations) : InvitationPersistence {
    companion object {
        val sharedInvitations: MutableMap<String, Invitation> = mutableMapOf()
    }
}