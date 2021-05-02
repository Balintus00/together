package hu.bme.aut.android.together.persistence.repository

import hu.bme.aut.android.together.model.domain.DomainEventQuestion
import hu.bme.aut.android.together.model.persistence.PersistedEventQuestion
import hu.bme.aut.android.together.persistence.dao.EventDataDao
import hu.bme.aut.android.together.persistence.dao.EventQuestionDao
import javax.inject.Inject

class EventQuestionsAndTitleRepository @Inject constructor(
    private val questionDao: EventQuestionDao,
    private val eventDataDao: EventDataDao
) {

    fun saveEventQuestionsByEventId(eventId: Long, listOfQuestions: List<DomainEventQuestion>) {
        questionDao.insertEventQuestion(*listOfQuestions.map { it.toPersistedEventQuestion() }
            .toTypedArray())
    }

    fun getEventTitleById(eventId: Long): String {
        return eventDataDao.getEventTitleById(eventId)
    }

    fun getEventQuestionsById(eventId: Long): List<DomainEventQuestion> {
        return questionDao.getEventQuestionsById(eventId).map { it.toDomainEventQuestion() }
    }

    private fun PersistedEventQuestion.toDomainEventQuestion(): DomainEventQuestion {

    }

    private fun DomainEventQuestion.toPersistedEventQuestion(): PersistedEventQuestion {

    }

}