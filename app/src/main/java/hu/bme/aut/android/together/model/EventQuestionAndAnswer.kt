package hu.bme.aut.android.together.model

import hu.bme.aut.android.together.model.presentation.EventMessage

/**
 * Contains two [EventMessage] references. One instance represents a question's data, the
 * other represents an answer's data.
 */
class EventQuestionAndAnswer(val question: EventMessage, val answer: EventMessage)