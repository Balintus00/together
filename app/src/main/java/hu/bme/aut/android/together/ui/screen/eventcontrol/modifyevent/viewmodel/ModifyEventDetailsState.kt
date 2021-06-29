package hu.bme.aut.android.together.ui.screen.eventcontrol.modifyevent.viewmodel

import hu.bme.aut.android.together.ui.model.EventDetails

sealed class ModifyEventDetailsState

object Loading: ModifyEventDetailsState()

data class EventDetailsLoaded(val eventDetails: EventDetails): ModifyEventDetailsState()

data class EventModificationHappened(val wasSuccessful: Boolean): ModifyEventDetailsState()
