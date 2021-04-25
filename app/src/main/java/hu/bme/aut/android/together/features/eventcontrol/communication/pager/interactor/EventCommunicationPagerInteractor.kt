package hu.bme.aut.android.together.features.eventcontrol.communication.pager.interactor

import hu.bme.aut.android.together.model.domain.DomainCommunicationPagerData
import hu.bme.aut.android.together.network.NetworkDataSource
import hu.bme.aut.android.together.persistence.repository.EventCommunicationPagerDataRepository
import javax.inject.Inject

class EventCommunicationPagerInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val repository: EventCommunicationPagerDataRepository
) {

    fun loadPagerDataByEventId(eventId: Long): DomainCommunicationPagerData {
        return networkDataSource.getCommunicationPagerDataById(eventId)?.let {
            repository.persistCommunicationPagerData(it)
            it
        } ?: repository.loadCommunicationPagerDataById(eventId)
    }

}