package hu.bme.aut.android.together.features.currentevents.viewmodel

import hu.bme.aut.android.together.model.presentation.EventShortInfo

sealed class EventListState

object Loading : EventListState()

data class EventListLoaded(
    val eventShortInfoList : List<EventShortInfo>
) : EventListState()