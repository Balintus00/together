package hu.bme.aut.android.together.ui.screen.eventcontrol.sendinvitation.viewmodel

import hu.bme.aut.android.together.ui.model.EventShortInfo

sealed class EventInvitationSenderState

object Loading: EventInvitationSenderState()

data class EventInformationLoaded(val shortInfo: EventShortInfo): EventInvitationSenderState()

data class InvitationSendingOperationEnded(val wasSuccessful: Boolean): EventInvitationSenderState()