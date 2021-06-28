package hu.bme.aut.android.together.data.disk.repository

import hu.bme.aut.android.together.model.domain.DomainEventInvitation
import hu.bme.aut.android.together.data.disk.model.PersistedEventInvitation
import hu.bme.aut.android.together.persistence.dao.EventInvitationsDao
import javax.inject.Inject

class EventInvitationsRepository @Inject constructor(
    private val eventInvitationsDao: EventInvitationsDao
) {

    fun saveIncomingEventInvitations(vararg invitations: DomainEventInvitation) {
        eventInvitationsDao.insertIncomingEventInvitations(*invitations.map {
            it.toPersistedEventInvitation()
        }.toTypedArray())
    }

    fun loadIncomingEventInvitations(): List<DomainEventInvitation> {
        return eventInvitationsDao.getCachedIncomingEventInvitations().map {
            it.toDomainEventInvitation()
        }
    }

    private fun DomainEventInvitation.toPersistedEventInvitation(): PersistedEventInvitation {
        return PersistedEventInvitation(id, title, author, message)
    }

    private fun PersistedEventInvitation.toDomainEventInvitation(): DomainEventInvitation {
        return DomainEventInvitation(id, title, author, message)
    }

}