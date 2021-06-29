package hu.bme.aut.android.together.data.disk.repository

import hu.bme.aut.android.together.domain.model.DomainEventQuestion
import hu.bme.aut.android.together.data.disk.model.PersistedEventQuestion
import hu.bme.aut.android.together.data.disk.dao.EventDataDao
import hu.bme.aut.android.together.data.disk.dao.EventQuestionDao
import javax.inject.Inject

class EventQuestionsAndTitleRepository @Inject constructor(
    private val questionDao: EventQuestionDao,
    private val eventDataDao: EventDataDao
) {

    fun saveEventQuestions(listOfQuestions: List<DomainEventQuestion>) {
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
        return DomainEventQuestion(questionId, question, author, detailedQuestion, eventId)
    }

    private fun DomainEventQuestion.toPersistedEventQuestion(): PersistedEventQuestion {
        return PersistedEventQuestion(id, question, author, detailedQuestion, eventId)
    }

}