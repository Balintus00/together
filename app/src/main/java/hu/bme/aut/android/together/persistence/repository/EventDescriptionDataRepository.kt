package hu.bme.aut.android.together.persistence.repository

import hu.bme.aut.android.together.model.domain.DomainEventDescriptionData
import hu.bme.aut.android.together.model.persistence.PersistedEventDescriptionData
import hu.bme.aut.android.together.model.persistence.PersistedEventDetails
import hu.bme.aut.android.together.persistence.dao.EventDetailsDao
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventDescriptionDataRepository @Inject constructor(
    private val eventDetailsDao: EventDetailsDao
) {

    fun saveEventDescriptionData(savableData: DomainEventDescriptionData) {
        eventDetailsDao.insertCachedEventDescriptionDetails(savableData.toPersistedEventDescriptionData())
    }

    fun loadEventDescriptionData(eventId: Long): DomainEventDescriptionData {
        return eventDetailsDao.getCachedEventDetailsById(eventId).toDomainEventDescriptionData()
    }

    private fun PersistedEventDetails.toDomainEventDescriptionData(): DomainEventDescriptionData {
        return DomainEventDescriptionData(
            id,
            title,
            SimpleDateFormat("yyyy.MM.d. HH:mm", Locale.ENGLISH).run { parse(startDate) }!!,
            SimpleDateFormat("yyyy.MM.d. HH:mm", Locale.ENGLISH).run { parse(endDate) }!!,
            location,
            description
        )
    }

    private fun DomainEventDescriptionData.toPersistedEventDescriptionData(): PersistedEventDescriptionData {
        return PersistedEventDescriptionData(
            id,
            title,
            SimpleDateFormat("yyyy.MM.d. HH:mm", Locale.ENGLISH).format(startDateTime),
            SimpleDateFormat("yyyy.MM.d. HH:mm", Locale.ENGLISH).format(endDateTime),
            location,
            description
        )
    }

}