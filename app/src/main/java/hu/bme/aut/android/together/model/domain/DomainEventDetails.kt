package hu.bme.aut.android.together.model.domain

import java.util.*

class DomainEventDetails(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val startDate: Date,
    val endDate: Date,
    val location: String,
    val description: String,
    val isParticipantCountLimited: Boolean,
    val maxParticipantCount: Int,
    val currentParticipantCount: Int,
    val isPrivate: Boolean,
    val isParticipant: Boolean,
    val isOrganiser: Boolean
)