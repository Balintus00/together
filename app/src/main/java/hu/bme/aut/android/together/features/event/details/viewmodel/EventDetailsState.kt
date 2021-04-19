package hu.bme.aut.android.together.features.event.details.viewmodel

import hu.bme.aut.android.together.model.presentation.EventDetails

sealed class EventDetailsState

object Loading : EventDetailsState()

data class EventDetailsLoaded(val details: EventDetails) : EventDetailsState()

