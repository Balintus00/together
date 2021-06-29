package hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.interactor.EventQuestionsInteractor
import hu.bme.aut.android.together.domain.model.DomainEventQuestion
import hu.bme.aut.android.together.domain.model.DomainEventQuestionsWithTitle
import hu.bme.aut.android.together.ui.model.EventQuestion
import hu.bme.aut.android.together.ui.model.EventQuestionsWithTitle
import javax.inject.Inject

class EventQuestionsPresenter @Inject constructor(
    private val interactor: EventQuestionsInteractor
) {

    suspend fun getEventQuestions(eventId: Long) = withIOContext {
        interactor.getEventQuestionById(eventId).toEventQuestionsWithTitle()
        }

    private fun DomainEventQuestionsWithTitle.toEventQuestionsWithTitle(): EventQuestionsWithTitle {
        return EventQuestionsWithTitle(title, questions.map { it.toEventQuestion() })
    }

    private fun DomainEventQuestion.toEventQuestion(): EventQuestion {
        return EventQuestion(question, author, detailedQuestion)
    }

}