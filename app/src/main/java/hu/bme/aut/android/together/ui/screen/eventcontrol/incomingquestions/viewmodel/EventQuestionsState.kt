package hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.viewmodel

import hu.bme.aut.android.together.ui.model.EventQuestionsWithTitle

sealed class EventQuestionsState

object Loading: EventQuestionsState()

data class EventQuestionsLoaded(val questionsWithTitle: EventQuestionsWithTitle) : EventQuestionsState()
