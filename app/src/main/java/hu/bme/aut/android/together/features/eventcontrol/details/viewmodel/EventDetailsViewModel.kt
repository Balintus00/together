package hu.bme.aut.android.together.features.eventcontrol.details.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.features.eventcontrol.details.presenter.EventDetailsPresenter
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val presenter: EventDetailsPresenter
) : RainbowCakeViewModel<EventDetailsState>(Loading) {

    fun loadEventDetails(eventId: Long) = execute {
        viewState = Loading
        presenter.getEventDetailsById(eventId).let {
            viewState = EventDetailsLoaded(it)
        }
    }

}