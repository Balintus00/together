package hu.bme.aut.android.together.ui.screen.eventcontrol.communication.qa.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.domain.interactor.EventQAInteractor
import hu.bme.aut.android.together.ui.model.EventAnswer
import hu.bme.aut.android.together.ui.model.EventQuestion
import hu.bme.aut.android.together.ui.model.EventQuestionAndAnswer
import javax.inject.Inject

class EventQAPresenter @Inject constructor(
    private val interactor: EventQAInteractor
) {

    suspend fun getEventQuestionsAndAnswersByEventId(eventId: Long) = withIOContext {
        interactor.getEventQuestionsAndAnswersById(eventId).map {
            EventQuestionAndAnswer(
                it.question.run { EventQuestion(question, author, detailedQuestion) },
                it.answer.run { EventAnswer(answer, author, detailedAnswer) }
            )
        }
    }

}