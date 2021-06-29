package hu.bme.aut.android.together.ui.screen.currentevents.viewmodel

import hu.bme.aut.android.together.ui.model.EventShortInfo

sealed class EventListState

object Loading : EventListState()

data class EventListLoaded(
    val eventShortInfoList : List<EventShortInfo>
) : EventListState()

data class LoadingError(
    val errorMessage: String
) : EventListState()