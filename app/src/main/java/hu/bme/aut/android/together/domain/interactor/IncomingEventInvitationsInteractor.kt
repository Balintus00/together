package hu.bme.aut.android.together.domain.interactor

import hu.bme.aut.android.together.domain.model.DomainEventInvitation
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventInvitationsRepository
import javax.inject.Inject

class IncomingEventInvitationsInteractor @Inject constructor(
    private val networkManager: NetworkDataSource,
    private val eventInvitationsRepository: EventInvitationsRepository
) {

    fun getUsersIncomingInvites(id: Long): List<DomainEventInvitation> {
        return networkManager.getIncomingInvitesById(id)?.let { eventMessageList ->
            eventInvitationsRepository.saveIncomingEventInvitations(*eventMessageList.toTypedArray())
            eventMessageList
        } ?: eventInvitationsRepository.loadIncomingEventInvitations()
    }
}