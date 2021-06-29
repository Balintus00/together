package hu.bme.aut.android.together.ui.screen.eventcontrol.details.interactor

import android.util.Log
import hu.bme.aut.android.together.domain.model.DomainEventDetails
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventDetailsRepository
import javax.inject.Inject

class EventDetailsInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val repository: EventDetailsRepository
) {

    fun getEventDetailsByEventId(eventId: Long) : DomainEventDetails {
        return networkDataSource.getEventDetailsById(eventId)?.let { eventDetails ->
            Log.d("Together!", "Hello!")
            repository.saveEventDetails(eventDetails)
            Log.d("Together!", "Hello!")
            eventDetails
        } ?: repository.loadEventDetailsById(eventId)
    }

}