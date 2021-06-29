package hu.bme.aut.android.together.ui.screen.incomiginvitations.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.ui.screen.incomiginvitations.presenter.IncomingEventInvitationsPresenter
import javax.inject.Inject

@HiltViewModel
class IncomingEventInvitationsViewModel @Inject constructor(private val presenter: IncomingEventInvitationsPresenter) :
    RainbowCakeViewModel<IncomingEventInvitationsState>(Loading) {

    fun loadIncomingInvitesByProfileId(id: Long) = execute {
        viewState = Loading
        presenter.getIncomingInvites(id).let {
            viewState = IncomingEventInvitationsLoaded(it)
        }
    }

}