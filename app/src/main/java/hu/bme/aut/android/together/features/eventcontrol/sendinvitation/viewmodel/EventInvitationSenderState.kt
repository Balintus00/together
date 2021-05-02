package hu.bme.aut.android.together.features.eventcontrol.sendinvitation.viewmodel

import hu.bme.aut.android.together.model.presentation.EventShortInfo

sealed class EventInvitationSenderState

object Loading: EventInvitationSenderState()

data class EventInformationLoaded(val shortInfo: EventShortInfo): EventInvitationSenderState()

data class InvitationSendingOperationEnded(val wasSuccessful: Boolean): EventInvitationSenderState()