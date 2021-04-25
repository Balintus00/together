package hu.bme.aut.android.together.features.eventcontrol.communication.pager.viewmodel

import hu.bme.aut.android.together.model.presentation.CommunicationPagerData

sealed class EventCommunicationPagerState

object Loading: EventCommunicationPagerState()

data class DataLoaded(val data: CommunicationPagerData): EventCommunicationPagerState()