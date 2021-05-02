package hu.bme.aut.android.together.features.eventcontrol.incomingquestions.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.features.eventcontrol.incomingquestions.presenter.EventQuestionsPresenter
import hu.bme.aut.android.together.model.domain.DomainEventQuestion
import hu.bme.aut.android.together.model.presentation.EventQuestion
import javax.inject.Inject

@HiltViewModel
class EventQuestionsViewModel @Inject constructor(
    private val presenter: EventQuestionsPresenter
) : RainbowCakeViewModel<EventQuestionsState>(Loading) {

    //TODO some kind of authentication and authorization token should be used here later
    fun loadEventQuestions(eventId: Long) = execute {
        viewState = Loading
        presenter.getEventQuestions(eventId).let {
            viewState = EventQuestionsLoaded(it)
        }
    }
}