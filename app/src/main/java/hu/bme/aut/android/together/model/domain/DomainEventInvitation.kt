package hu.bme.aut.android.together.model.domain

/**
 * TODO should be changed
 * Stores data, that can be used to represent messages in the application.
 * Stores the message's title, author, and the message.
 */
class DomainEventInvitation(
    val id: Long,
    val title: String,
    val author: String,
    val message: String
)