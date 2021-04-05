package hu.bme.aut.android.together.features.incomiginvitations.viewmodel

import hu.bme.aut.android.together.model.presentation.EventMessage

sealed class IncomingEventInvitationsState

object Loading : IncomingEventInvitationsState()

data class IncomingEventInvitationsLoaded(
    val invites: List<EventMessage>
) : IncomingEventInvitationsState()