package hu.bme.aut.android.together.model.domain

/**
 * Stores data, that can be used to represent messages in the application.
 * Stores the message's title, author, and the message.
 */
class DomainEventMessage(val title: String, val author: String, val message: String)