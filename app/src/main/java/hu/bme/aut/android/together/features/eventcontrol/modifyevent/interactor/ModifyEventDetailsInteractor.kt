package hu.bme.aut.android.together.features.eventcontrol.modifyevent.interactor

import hu.bme.aut.android.together.model.domain.DomainEventDetails
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventDetailsRepository
import javax.inject.Inject

class ModifyEventDetailsInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val repository: EventDetailsRepository
) {

    fun getCurrentEventDetails(eventId: Long): DomainEventDetails {
        return networkDataSource.getEventDetailsById(eventId)?.let {
            repository.saveEventDetails(it)
            it
        } ?: repository.loadEventDetailsById(eventId)
    }

    fun uploadModifiedEventDetails(eventId: Long, eventDetails: DomainEventDetails): Boolean {
        return networkDataSource.modifyEvent(eventId, eventDetails)
    }

}