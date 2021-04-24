package hu.bme.aut.android.together.features.eventcontrol.wholedescription.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.features.eventcontrol.wholedescription.presenter.EventWholeDescriptionPresenter
import javax.inject.Inject

@HiltViewModel
class EventWholeDescriptionViewModel @Inject constructor(
    private val presenter: EventWholeDescriptionPresenter
) : RainbowCakeViewModel<EventWholeDescriptionState>(Loading) {

    fun loadDescriptionScreenData(eventId: Long) = execute {
        viewState = Loading
        presenter.getEventDescriptionScreenData(eventId).let {
            viewState = EventWholeDescriptionLoaded(it)
        }
    }

}