package hu.bme.aut.android.together.features.eventcontrol.sendinvitation.interactor

import hu.bme.aut.android.together.model.domain.DomainEventShortInfo
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.InvitationSenderRepository
import javax.inject.Inject

class EventInvitationSenderInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val repository: InvitationSenderRepository
) {

    fun getEventShortInfo(eventId: Long): DomainEventShortInfo {
        return networkDataSource.getEventShortInfoByEventId(eventId)?.let {
            repository.persistEventShortInfo(it)
            it
        } ?: repository.loadEventShortInfo(eventId)
    }

    fun sendEventInvitationsAndGetResponse(eventId: Long, usernameList: List<String>): Boolean {
        return networkDataSource.sendInvitations(eventId, usernameList)
    }
}