package com.nilo.skipmo.lobby

import java.util.*

internal interface InvitationPersistence {

    val invitations: MutableMap<String, Invitation>
    fun getInvitations() : Collection<Invitation> = invitations.values
    fun getInvitations(user : User ) : Collection<Invitation> = invitations.values.filter{
        it.user1 == user || it.user2 == user}
    fun getInvitation(id: String) = invitations[id]
    fun addInvitation(user1: User, user2: User, id: String = UUID.randomUUID().toString()) : Invitation {
        val invitation = Invitation(id, user1, user2)
        invitations[id] = invitation
        return invitation
    }
    fun deleteInvitation(id: String) =
            invitations.remove(id) ?: throw Exception("Invitation not found for id=$id")





}
