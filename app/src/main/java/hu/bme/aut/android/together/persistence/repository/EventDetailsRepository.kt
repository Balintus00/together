package hu.bme.aut.android.together.persistence.repository

import hu.bme.aut.android.together.model.domain.DomainEventDetails
import hu.bme.aut.android.together.model.persistence.PersistedEventDetails
import hu.bme.aut.android.together.persistence.dao.EventDetailsDao
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventDetailsRepository @Inject constructor(
    private val eventDetailsDao: EventDetailsDao
) {

    fun saveEventDetails(eventDetails: DomainEventDetails) {
        eventDetailsDao.cacheEventDetails(eventDetails.toPersistedEventDetails())
    }

    fun loadEventDetailsById(eventId: Long): DomainEventDetails {
        return eventDetailsDao.getCachedEventDetailsById(eventId).toDomainEventDetails()
    }

    private fun DomainEventDetails.toPersistedEventDetails(): PersistedEventDetails {
        return PersistedEventDetails(
            id,
            title,
            imageUrl,
            SimpleDateFormat("yyyy.MM.d. HH:mm", Locale.ENGLISH).format(startDate),
            SimpleDateFormat("yyyy.MM.d. HH:mm", Locale.ENGLISH).format(endDate),
            location,
            description,
            isParticipantCountLimited,
            maxParticipantCount,
            currentParticipantCount,
            isPrivate,
            isParticipant,
            isOrganiser
        )
    }

    private fun PersistedEventDetails.toDomainEventDetails(): DomainEventDetails {
        return DomainEventDetails(
            id,
            title,
            imageUrl,
            SimpleDateFormat("yyyy.MM.d. HH:mm", Locale.ENGLISH).run { parse(startDate) }!!,
            SimpleDateFormat("yyyy.MM.d. HH:mm", Locale.ENGLISH).run { parse(endDate) }!!,
            location,
            description,
            isParticipantCountLimited,
            maxParticipantCount,
            currentParticipantCount,
            isPrivate,
            isParticipant,
            isOrganiser
        )
    }

}