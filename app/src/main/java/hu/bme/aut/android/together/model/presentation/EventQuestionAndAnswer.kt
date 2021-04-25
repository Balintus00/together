package hu.bme.aut.android.together.model.presentation

/**
 * Contains two [EventInvitation] references. One instance represents a question's data, the
 * other represents an answer's data.
 */
data class EventQuestionAndAnswer(val question: EventQuestion, val answer: EventAnswer)

class EventQuestion(val question: String, val author: String, val detailedQuestion: String)

class EventAnswer(val answer: String, val author: String, val detailedAnswer: String)