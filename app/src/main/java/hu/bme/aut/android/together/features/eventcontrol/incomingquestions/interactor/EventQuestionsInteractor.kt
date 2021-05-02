package hu.bme.aut.android.together.features.eventcontrol.incomingquestions.interactor

import hu.bme.aut.android.together.model.domain.DomainEventQuestionsWithTitle
import hu.bme.aut.android.together.network.NetworkDataSource
import hu.bme.aut.android.together.persistence.repository.EventQuestionsAndTitleRepository
import javax.inject.Inject

class EventQuestionsInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val eventQuestionsAndTitleRepository: EventQuestionsAndTitleRepository
) {

    fun getEventQuestionById(eventId: Long): DomainEventQuestionsWithTitle {
        return networkDataSource.getEventQuestionsAndTitle(eventId)?.let {
            eventQuestionsAndTitleRepository.saveEventQuestions(it.questions)
            it
        } ?: DomainEventQuestionsWithTitle(
            eventId,
            eventQuestionsAndTitleRepository.getEventTitleById(eventId),
            eventQuestionsAndTitleRepository.getEventQuestionsById(eventId)
        )
    }

}