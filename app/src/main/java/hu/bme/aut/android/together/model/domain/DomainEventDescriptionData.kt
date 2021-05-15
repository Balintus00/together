package hu.bme.aut.android.together.model.domain

import java.util.*

class DomainEventDescriptionData(
    val id: Long,
    val title: String,
    val startDateTime: Date,
    val endDateTime: Date,
    val location: String,
    val description: String
)