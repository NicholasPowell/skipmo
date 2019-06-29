package com.nilo.skipmo.lobby.persistence

import com.nilo.skipmo.lobby.Invitation
import com.nilo.skipmo.lobby.InvitationPersistence

internal class ThrowAwayInMemoryInvitationPersistence(
        override val invitations: MutableMap<String, Invitation> = mutableMapOf()) : InvitationPersistence