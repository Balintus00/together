package hu.bme.aut.android.together.model

/**
 * Contains two [EventNewsMessage] references. One instance represents a question's data, the
 * other represents an answer's data.
 */
class EventQuestionAndAnswer(val question: EventNewsMessage, val answer: EventNewsMessage)