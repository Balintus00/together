package hu.bme.aut.android.together.domain.interactor

import hu.bme.aut.android.together.domain.model.DomainCommunicationPagerData
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventCommunicationPagerDataRepository
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