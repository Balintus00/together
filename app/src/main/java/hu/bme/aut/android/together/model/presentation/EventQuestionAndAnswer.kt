package hu.bme.aut.android.together.model.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Contains two [EventInvitation] references. One instance represents a question's data, the
 * other represents an answer's data.
 */
data class EventQuestionAndAnswer(val question: EventQuestion, val answer: EventAnswer)

@Parcelize
data class EventQuestion(val question: String, val author: String, val detailedQuestion: String): Parcelable

class EventAnswer(val answer: String, val author: String, val detailedAnswer: String)