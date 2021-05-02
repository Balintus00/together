package hu.bme.aut.android.together.features.eventcontrol.modifyevent.viewmodel

import hu.bme.aut.android.together.model.presentation.EventDetails

sealed class ModifyEventDetailsState

object Loading: ModifyEventDetailsState()

data class EventDetailsLoaded(val eventDetails: EventDetails): ModifyEventDetailsState()

data class EventModificationHappened(val wasSuccessful: Boolean): ModifyEventDetailsState()
