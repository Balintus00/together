package hu.bme.aut.android.together.features.incomiginvitations.interactor

import hu.bme.aut.android.together.model.domain.DomainEventInvitation
import hu.bme.aut.android.together.network.NetworkDataSource
import hu.bme.aut.android.together.persistence.repository.EventInvitationsRepository
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