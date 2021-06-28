package hu.bme.aut.android.together.data.disk.repository

import hu.bme.aut.android.together.model.domain.DomainEventAnswer
import hu.bme.aut.android.together.model.domain.DomainEventQuestion
import hu.bme.aut.android.together.model.domain.DomainEventQuestionAndAnswer
import hu.bme.aut.android.together.data.disk.model.PersistedEventAnswer
import hu.bme.aut.android.together.data.disk.model.PersistedEventQuestion
import hu.bme.aut.android.together.data.disk.model.PersistedQuestionAndAnswer
import hu.bme.aut.android.together.persistence.dao.EventAnswerDao
import hu.bme.aut.android.together.persistence.dao.EventQuestionDao
import hu.bme.aut.android.together.persistence.dao.QuestionAndAnswerDao
import javax.inject.Inject

class EventQuestionAndAnswerRepository @Inject constructor(
    private val questionAndAnswerDao: QuestionAndAnswerDao,
    private val questionDao: EventQuestionDao,
    private val answerDao: EventAnswerDao
) {

    fun persistQuestionAndAnswer(questionAndAnswerList: List<DomainEventQuestionAndAnswer>) {
        questionAndAnswerList.map {
            PersistedQuestionAndAnswer(
                it.question.run {
                    PersistedEventQuestion(
                        id,
                        question,
                        author,
                        detailedQuestion,
                        eventId
                    )
                },
                it.answer.run {
                    PersistedEventAnswer(
                        id,
                        answer,
                        author,
                        detailedAnswer,
                        eventId,
                        it.question.id
                    )
                }
            )
        }.forEach {
            questionDao.insertEventQuestion(it.question)
            answerDao.insertEventAnswer(it.answer)
        }
    }

    fun loadCachedQuestionsAndAnswersByEventId(eventId: Long): List<DomainEventQuestionAndAnswer> {
        return questionAndAnswerDao.getEventsQuestionsAndAnswers(eventId).map {
            DomainEventQuestionAndAnswer(
                it.question.run {
                    DomainEventQuestion(
                        questionId,
                        question,
                        author,
                        detailedQuestion,
                        this.eventId
                    )
                },
                it.answer.run {
                    DomainEventAnswer(
                        answerId,
                        answer,
                        author,
                        detailedAnswer,
                        this.eventId
                    )
                },
                it.question.eventId
            )
        }
    }

}