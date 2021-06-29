package hu.bme.aut.android.together.ui.screen.incomiginvitations.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.domain.interactor.IncomingEventInvitationsInteractor
import hu.bme.aut.android.together.ui.model.EventInvitation
import javax.inject.Inject

class IncomingEventInvitationsPresenter @Inject constructor(
    private val interactor: IncomingEventInvitationsInteractor
) {

    suspend fun getIncomingInvites(id: Long): List<EventInvitation> = withIOContext {
        interactor.getUsersIncomingInvites(id).map {
            EventInvitation(
                it.title,
                it.author,
                it.message
            )
        }
    }

}