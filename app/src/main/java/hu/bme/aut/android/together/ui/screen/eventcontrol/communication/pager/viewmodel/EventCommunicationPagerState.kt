package hu.bme.aut.android.together.ui.screen.eventcontrol.communication.pager.viewmodel

import hu.bme.aut.android.together.ui.model.CommunicationPagerData

sealed class EventCommunicationPagerState

object Loading: EventCommunicationPagerState()

data class DataLoaded(val data: CommunicationPagerData): EventCommunicationPagerState()