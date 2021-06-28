package hu.bme.aut.android.together.features.eventcontrol.wholedescription.interactor

import hu.bme.aut.android.together.domain.model.DomainEventDescriptionData
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventDescriptionDataRepository
import javax.inject.Inject

class EventWholeDescriptionInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val repository: EventDescriptionDataRepository
) {

    fun getEventDescriptionDataByEventId(eventId: Long) : DomainEventDescriptionData {
        return networkDataSource.getEventDescriptionDataById(eventId)?.let { domainData ->
            repository.saveEventDescriptionData(domainData)
            domainData
        } ?: repository.loadEventDescriptionData(eventId)
    }
}