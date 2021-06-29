package hu.bme.aut.android.together.eventcontrol.communication.qa

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.google.common.truth.Truth
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.qa.presenter.EventQAPresenter
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.qa.viewmodel.EventQALoaded
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.qa.viewmodel.EventQAViewModel
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.qa.viewmodel.Loading
import hu.bme.aut.android.together.ui.model.EventQuestionAndAnswer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class EventQAViewModelTest: ViewModelTest() {

    private val mockEventQAPresenter = mock(EventQAPresenter::class.java)

    private lateinit var eventQAViewModel: EventQAViewModel

    @Before
    fun setUp() {
        eventQAViewModel  = EventQAViewModel(mockEventQAPresenter)
    }

    @Test
    fun checkInitialLoadingState() {
       Truth.assertThat(eventQAViewModel.state.value is Loading)
    }

    @Test
    fun checkViewStateAfterSuccessfulLoading() : Unit = runBlockingTest {
        val exampleEventId = 1L
        val exampleQuestionAndAnswerList = listOf<EventQuestionAndAnswer>()
        `when`(mockEventQAPresenter.getEventQuestionsAndAnswersByEventId(exampleEventId)).thenReturn(
            exampleQuestionAndAnswerList
        )
        eventQAViewModel.observeStateAndEvents { stateObserver, _ ->
            eventQAViewModel.loadQuestionsAndAnswersByEventId(exampleEventId)
            stateObserver.assertObserved(Loading, EventQALoaded(exampleQuestionAndAnswerList))
        }
    }

}