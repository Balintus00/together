package hu.bme.aut.android.together.ui.screen.eventcontrol.modifyevent.viewmodel

import androidx.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.ui.screen.eventcontrol.modifyevent.presenter.ModifyEventDetailsPresenter
import hu.bme.aut.android.together.ui.model.EventDetails
import javax.inject.Inject

@HiltViewModel
class ModifyEventDetailsViewModel @Inject constructor(
    private val presenter: ModifyEventDetailsPresenter
): RainbowCakeViewModel<ModifyEventDetailsState>(Loading) {

    val modifiedEventDetails: MutableLiveData<EventDetails> = MutableLiveData()

    fun loadCurrentEventDetailsByEventId(eventId: Long) = execute {
        viewState = Loading
        presenter.loadCurrentEventDetails(eventId).let {
            viewState = EventDetailsLoaded(it)
        }
    }

    fun sendModificationRequest(eventId: Long) = execute {
        viewState = Loading
        presenter.sendEventModificationRequest(eventId, modifiedEventDetails.value!!).let {
            viewState = EventModificationHappened(it)
        }
    }

}