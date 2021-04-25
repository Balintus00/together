package hu.bme.aut.android.together.persistence.repository

import hu.bme.aut.android.together.model.domain.DomainCommunicationPagerData
import hu.bme.aut.android.together.model.persistence.PersistedCommunicationPagerData
import hu.bme.aut.android.together.model.persistence.PersistedEventData
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