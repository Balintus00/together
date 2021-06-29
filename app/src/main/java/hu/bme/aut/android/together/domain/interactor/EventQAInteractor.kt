package hu.bme.aut.android.together.domain.interactor

import hu.bme.aut.android.together.domain.model.DomainEventQuestionAndAnswer
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventQuestionAndAnswerRepository
import javax.inject.Inject

class EventQAInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val repository: EventQuestionAndAnswerRepository
) {

    fun getEventQuestionsAndAnswersById(eventId: Long) : List<DomainEventQuestionAndAnswer> {
        return networkDataSource.getEventQuestionsAndAnswersByEventId(eventId)?.let {
            repository.persistQuestionAndAnswer(it)
            it
        } ?: repository.loadCachedQuestionsAndAnswersByEventId(eventId)
    }

}