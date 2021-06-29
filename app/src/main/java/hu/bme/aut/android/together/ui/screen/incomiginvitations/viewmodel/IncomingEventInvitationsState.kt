package hu.bme.aut.android.together.ui.screen.incomiginvitations.viewmodel

import hu.bme.aut.android.together.ui.model.EventInvitation

sealed class IncomingEventInvitationsState

object Loading : IncomingEventInvitationsState()

data class IncomingEventInvitationsLoaded(
    val invites: List<EventInvitation>
) : IncomingEventInvitationsState()