package hu.bme.aut.android.together.features.eventcontrol.wholedescription.viewmodel

import hu.bme.aut.android.together.model.presentation.EventDescriptionScreenData

sealed class EventWholeDescriptionState

object Loading : EventWholeDescriptionState()

data class EventWholeDescriptionLoaded(val descriptionData: EventDescriptionScreenData) :
    EventWholeDescriptionState()