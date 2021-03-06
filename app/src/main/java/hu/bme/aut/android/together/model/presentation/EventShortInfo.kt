package hu.bme.aut.android.together.model.presentation

/**
 * Contains the the most important data about an event, like its name, location, start data and time,
 * and its image's URL.
 */
data class EventShortInfo(
    val eventId: Long,
    val name: String,
    val location: String,
    val startDate: String,
    val endDate: String,
    val imageUrl: String
)