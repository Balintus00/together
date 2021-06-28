package hu.bme.aut.android.together.domain.model

import java.util.*

data class DomainEventShortInfo(
    val eventId: Long,
    val name: String,
    val location: String,
    val startDate: Date,
    val endDate: Date,
    val imageUrl: String
)