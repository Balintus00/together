package hu.bme.aut.android.together.features.incomiginvitations.interactor

import hu.bme.aut.android.together.model.domain.DomainEventMessage
import hu.bme.aut.android.together.network.NetworkManager
import javax.inject.Inject

class IncomingEventInvitationsInteractor @Inject constructor(
    private val networkManager: NetworkManager
) {

    fun getUsersIncomingInvites(id: Long) : List<DomainEventMessage> {
        return networkManager.getIncomingInvitesById(id).map {
            DomainEventMessage(
                it.title,
                it.author,
                it.message
            )
        }
    }
}