package hu.bme.aut.android.together.model.presentation

import java.util.*

class EventDetails(
    val title: String,
    val imageUrl: String,
    val startDate: String,
    val endDate: String,
    val location: String,
    val description: String,
    val isParticipantCountLimited: Boolean,
    val maxParticipantCount: Int,
    val currentParticipantCount: Int,
    val isPrivate: Boolean,
    val isParticipant: Boolean,
    val isOrganiser: Boolean
)