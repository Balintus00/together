package hu.bme.aut.android.together.persistence.repository

import hu.bme.aut.android.together.model.domain.DomainEventDetails
import hu.bme.aut.android.together.model.persistence.PersistedEventData
import hu.bme.aut.android.together.model.persistence.PersistedEventDetails
import hu.bme.aut.android.together.persistence.dao.EventDataDao
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventDetailsRepository @Inject constructor(
    private val eventDataDao: EventDataDao
) {

    fun saveEventDetails(eventDetails: DomainEventDetails) {
        eventDataDao.insertCachedEventDetails(eventDetails.toPersistedEventDetails())
    }

    fun loadEventDetailsById(eventId: Long): DomainEventDetails {
        return eventDataDao.getCachedEventDetailsById(eventId).toDomainEventDetails()
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

    private fun PersistedEventData.toDomainEventDetails(): DomainEventDetails {
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