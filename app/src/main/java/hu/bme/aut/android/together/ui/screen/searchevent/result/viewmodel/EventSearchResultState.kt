package hu.bme.aut.android.together.ui.screen.searchevent.result.viewmodel

import hu.bme.aut.android.together.ui.model.EventShortInfo

sealed class EventSearchResultState

object Loading : EventSearchResultState()

data class ResultsLoaded(val results: List<EventShortInfo>) : EventSearchResultState()