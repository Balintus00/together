package hu.bme.aut.android.together.ui.screen.eventcontrol.details.viewmodel

import hu.bme.aut.android.together.ui.model.EventDetails

sealed class EventDetailsState

object Loading : EventDetailsState()

data class EventDetailsLoaded(val details: EventDetails) : EventDetailsState()

