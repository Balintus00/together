package hu.bme.aut.android.together.ui.screen.eventcontrol.communication.qa.viewmodel

import hu.bme.aut.android.together.ui.model.EventQuestionAndAnswer

sealed class EventQAState

object Loading: EventQAState()

data class EventQALoaded(val qaList: List<EventQuestionAndAnswer>) : EventQAState()