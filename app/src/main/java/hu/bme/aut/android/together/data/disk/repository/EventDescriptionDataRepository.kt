package hu.bme.aut.android.together.data.disk.repository

import hu.bme.aut.android.together.domain.model.DomainEventDescriptionData
import hu.bme.aut.android.together.data.disk.model.PersistedEventDescriptionData
import hu.bme.aut.android.together.data.disk.model.PersistedEventData
import hu.bme.aut.android.together.persistence.dao.EventDataDao
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventDescriptionDataRepository @Inject constructor(
    private val eventDataDao: EventDataDao
) {

    fun saveEventDescriptionData(savableData: DomainEventDescriptionData) {
        eventDataDao.insertCachedEventDescriptionDetails(savableData.toPersistedEventDescriptionData())
    }

    fun loadEventDescriptionData(eventId: Long): DomainEventDescriptionData {
        return eventDataDao.getCachedEventDetailsById(eventId).toDomainEventDescriptionData()
    }

    private fun PersistedEventData.toDomainEventDescriptionData(): DomainEventDescriptionData {
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