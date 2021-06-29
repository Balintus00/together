package hu.bme.aut.android.together.ui.screen.eventcontrol.sendinvitation.viewmodel

import androidx.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.ui.screen.eventcontrol.sendinvitation.presenter.EventInvitationSenderPresenter
import javax.inject.Inject

@HiltViewModel
class EventInvitationSenderViewModel @Inject constructor(
    private val presenter: EventInvitationSenderPresenter
) : RainbowCakeViewModel<EventInvitationSenderState>(Loading) {

    val invitedUsers: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())

    fun loadEventDetails(eventId: Long) = execute {
        viewState = Loading
        presenter.loadEventDetails(eventId).let {
            viewState = EventInformationLoaded(it)
        }
    }

    fun sendInvitations(eventId: Long) = execute {
        viewState = Loading
        presenter.sendEventInvitations(eventId, invitedUsers.value!!).let {
            viewState = InvitationSendingOperationEnded(it)
        }
    }

}