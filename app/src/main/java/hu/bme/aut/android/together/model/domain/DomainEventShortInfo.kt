package hu.bme.aut.android.together.model.domain

import java.util.*

data class DomainEventShortInfo(
    val eventId: Long,
    val name: String,
    val location: String,
    val startDate: Date,
    val endDate: Date,
    val imageUrl: String,
    val isComing: Boolean
)