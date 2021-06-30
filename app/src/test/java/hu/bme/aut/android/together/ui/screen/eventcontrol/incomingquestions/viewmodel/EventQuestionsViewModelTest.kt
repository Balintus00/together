package hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.viewmodel

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.google.common.truth.Truth
import hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.presenter.EventQuestionsPresenter
import hu.bme.aut.android.together.ui.model.EventQuestion
import hu.bme.aut.android.together.ui.model.EventQuestionsWithTitle
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
class EventQuestionsViewModelTest: ViewModelTest() {

    private val mockPresenter: EventQuestionsPresenter = mock(EventQuestionsPresenter::class.java)

    private lateinit var eventQuestionsViewModel: EventQuestionsViewModel

    @Before
    fun setUp() {
        eventQuestionsViewModel = EventQuestionsViewModel(mockPresenter)
    }

    @Test
    fun checkInitialLoadingState() {
        Truth.assertThat(eventQuestionsViewModel.state.value is Loading)
    }

    @Test
    fun checkViewStateAfterSuccessfulLoading(): Unit = runBlockingTest {
        val exampleId = 1L
        val exampleData = EventQuestionsWithTitle("Lorem", listOf(EventQuestion("Lorem", "Ipsum", "Dolor")))
        `when`(mockPresenter.getEventQuestions(exampleId)).thenReturn(exampleData)
        eventQuestionsViewModel.observeStateAndEvents { stateObserver, _ ->
            eventQuestionsViewModel.loadEventQuestions(exampleId)
            stateObserver.assertObserved(Loading, EventQuestionsLoaded(exampleData))
        }
    }

}