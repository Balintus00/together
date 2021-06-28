package hu.bme.aut.android.together.features.eventcontrol.communication.qa.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.domain.interactor.EventQAInteractor
import hu.bme.aut.android.together.model.presentation.EventAnswer
import hu.bme.aut.android.together.model.presentation.EventQuestion
import hu.bme.aut.android.together.model.presentation.EventQuestionAndAnswer
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