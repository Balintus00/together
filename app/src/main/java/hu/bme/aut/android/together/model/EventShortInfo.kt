package hu.bme.aut.android.together.model

/**
 * Contains the the most important data about an event, like its name, location, start data and time,
 * and its image's URL.
 */
data class EventShortInfo(
    val name: String, val location: String, val time: String, val imageUrl: String
)