package hu.bme.aut.android.together.features.incomiginvitations.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import hu.bme.aut.android.together.features.incomiginvitations.presenter.IncomingEventInvitationsPresenter
import javax.inject.Inject

class IncomingEventInvitationsViewModel @Inject constructor(private val presenter: IncomingEventInvitationsPresenter) :
    RainbowCakeViewModel<IncomingEventInvitationsState>(Loading) {

    fun loadIncomingInvitesByProfileId(id: Long) = execute {
        viewState = Loading
        presenter.getIncomingInvites(id).let {
            viewState = IncomingEventInvitationsLoaded(it)
        }
    }

}