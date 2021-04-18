package hu.bme.aut.android.together.features.searchevent.result.viewmodel

import hu.bme.aut.android.together.model.presentation.EventShortInfo

sealed class EventSearchResultState

object Loading : EventSearchResultState()

data class ResultsLoaded(val results: List<EventShortInfo>) : EventSearchResultState()