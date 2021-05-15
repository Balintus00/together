package hu.bme.aut.android.together.features.eventcontrol.communication.qa.viewmodel

import hu.bme.aut.android.together.model.presentation.EventQuestionAndAnswer

sealed class EventQAState

object Loading: EventQAState()

data class EventQALoaded(val qaList: List<EventQuestionAndAnswer>) : EventQAState()