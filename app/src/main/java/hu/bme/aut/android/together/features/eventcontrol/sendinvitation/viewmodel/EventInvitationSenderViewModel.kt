package hu.bme.aut.android.together.features.eventcontrol.sendinvitation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.model.presentation.EventShortInfo
import javax.inject.Inject

@HiltViewModel
class EventInvitationSenderViewModel @Inject constructor(

) : RainbowCakeViewModel<EventInvitationSenderState>(Loading) {

    val invitedUsers:MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())

    fun loadEventDetails(eventId: Long) {
        //TODO
        viewState = Loading
        viewState = EventInformationLoaded(EventShortInfo(1L, "Kristóf's birthday party", "Budapest", "2021.05.02.","2021.05.03.", ""))
    }

    fun sendInvitations(eventId: Long) {
        //TODO
        viewState = Loading
        viewState = InvitationSendingOperationEnded(true)
    }

}