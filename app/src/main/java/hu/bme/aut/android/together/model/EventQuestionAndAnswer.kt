package hu.bme.aut.android.together.model

import hu.bme.aut.android.together.model.presentation.EventInvitation

/**
 * Contains two [EventInvitation] references. One instance represents a question's data, the
 * other represents an answer's data.
 */
class EventQuestionAndAnswer(val question: EventInvitation, val answer: EventInvitation)