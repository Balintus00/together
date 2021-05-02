package hu.bme.aut.android.together.features.eventcontrol.incomingquestions.viewmodel

import hu.bme.aut.android.together.model.presentation.EventQuestionsWithTitle

sealed class EventQuestionsState

object Loading: EventQuestionsState()

data class EventQuestionsLoaded(val questionsWithTitle: EventQuestionsWithTitle) : EventQuestionsState()
