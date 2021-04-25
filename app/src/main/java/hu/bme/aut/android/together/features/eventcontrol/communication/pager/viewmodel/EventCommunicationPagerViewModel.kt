package hu.bme.aut.android.together.features.eventcontrol.communication.pager.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.features.eventcontrol.communication.pager.presenter.EventCommunicationPagerPresenter
import javax.inject.Inject

@HiltViewModel
class EventCommunicationPagerViewModel @Inject constructor(
    private val presenter: EventCommunicationPagerPresenter
) : RainbowCakeViewModel<EventCommunicationPagerState>(Loading) {

    fun loadRepresentedData(eventId: Long) = execute {
        viewState = Loading
        presenter.loadPagerDataByEventId(eventId).let {
            viewState = DataLoaded(it)
        }
    }

}