package hu.bme.aut.android.together.model.presentation

/**
 * Stores data, that can be used to represent messages in the application.
 * Stores the message's title, author, and the message.
 */
data class EventMessage(val title: String, val author: String, val message: String)