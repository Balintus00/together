package hu.bme.aut.android.together.features.eventcontrol.communication.qa.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.features.eventcontrol.communication.qa.presenter.EventQAPresenter
import javax.inject.Inject

@HiltViewModel
class EventQAViewModel @Inject constructor(
    private val presenter: EventQAPresenter
) : RainbowCakeViewModel<EventQAState>(Loading) {

    fun loadQuestionsAndAnswersByEventId(eventId: Long) = execute {
        viewState = Loading
        presenter.getEventQuestionsAndAnswersByEventId(eventId).let {
            viewState = EventQALoaded(it)
        }
    }

}