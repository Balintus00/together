package hu.bme.aut.android.together.ui.screen.eventcontrol.wholedescription.viewmodel

import hu.bme.aut.android.together.ui.model.EventDescriptionScreenData

sealed class EventWholeDescriptionState

object Loading : EventWholeDescriptionState()

data class EventWholeDescriptionLoaded(val descriptionData: EventDescriptionScreenData) :
    EventWholeDescriptionState()