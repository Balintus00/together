package hu.bme.aut.android.together.model

import android.location.Location

/**
 * This class's instances holds every important data about an event.
 */
@Suppress("unused") //TODO this class will be used in the next release
class EventData(
    val name: String,
    val startDateTime: String,
    val endDateTime: String,
    val string: Location,
    val description: String,
    val imageURL: String,
    val isPrivateEvent: Boolean,
    val isUserOrganiser: Boolean,
    val isUserParticipant: Boolean,
    val areSpacesLimited: Boolean,
    val maximumParticipantCount: Int
)