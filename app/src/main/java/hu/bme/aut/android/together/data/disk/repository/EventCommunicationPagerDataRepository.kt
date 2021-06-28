package hu.bme.aut.android.together.data.disk.repository

import hu.bme.aut.android.together.domain.model.DomainCommunicationPagerData
import hu.bme.aut.android.together.data.disk.model.PersistedCommunicationPagerData
import hu.bme.aut.android.together.data.disk.model.PersistedEventData
import hu.bme.aut.android.together.persistence.dao.EventDataDao
import javax.inject.Inject

class EventCommunicationPagerDataRepository @Inject constructor(
    private val eventDataDao: EventDataDao
) {

    fun persistCommunicationPagerData(persistedData: DomainCommunicationPagerData) {
        eventDataDao.insertCachedEventCommunicationPagerData(persistedData.toPersistedCommunicationPagerData())
    }

    fun loadCommunicationPagerDataById(eventId: Long): DomainCommunicationPagerData {
        return eventDataDao.getCachedEventDetailsById(eventId).toDomainCommunicationPagerData()
    }

    private fun DomainCommunicationPagerData.toPersistedCommunicationPagerData(): PersistedCommunicationPagerData {
        return PersistedCommunicationPagerData(
            id, eventTitle, isOrganiser, organiserQuestionCount
        )
    }

    private fun PersistedEventData.toDomainCommunicationPagerData(): DomainCommunicationPagerData {
        return DomainCommunicationPagerData(
            id, title, isOrganiser, organiserQuestionCount
        )
    }

}