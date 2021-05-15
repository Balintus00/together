package hu.bme.aut.android.together.model.domain

data class DomainEventQuestionAndAnswer(
    val question: DomainEventQuestion,
    val answer: DomainEventAnswer,
    val eventId: Long
)

class DomainEventQuestion(
    val id: Long,
    val question: String,
    val author: String,
    val detailedQuestion: String,
    val eventId: Long
)

class DomainEventAnswer(
    val id: Long,
    val answer: String,
    val author: String,
    val detailedAnswer: String,
    val eventId: Long
)