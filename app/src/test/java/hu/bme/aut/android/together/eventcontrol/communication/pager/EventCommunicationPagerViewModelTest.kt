package hu.bme.aut.android.together.eventcontrol.communication.pager

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.google.common.truth.Truth
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.pager.presenter.EventCommunicationPagerPresenter
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.pager.viewmodel.DataLoaded
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.pager.viewmodel.EventCommunicationPagerViewModel
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.pager.viewmodel.Loading
import hu.bme.aut.android.together.ui.model.CommunicationPagerData
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
class EventCommunicationPagerViewModelTest : ViewModelTest() {

    private val mockEventCommunicationPagerPresenter = mock(EventCommunicationPagerPresenter::class.java)

    private lateinit var eventCommunicationPagerViewModel: EventCommunicationPagerViewModel

    @Before
    fun setUp() {
        eventCommunicationPagerViewModel = EventCommunicationPagerViewModel(mockEventCommunicationPagerPresenter)
    }

    @Test
    fun checkInitialLoadingState() {
        Truth.assertThat(eventCommunicationPagerViewModel.state.value is Loading)
    }

    @Test
    fun checkViewStateAfterSuccessfulLoading() : Unit = runBlockingTest {
        val exampleEventId = 1L
        val exampleCommunicationPagerData = CommunicationPagerData(
            "example", true, 1
        )
        `when`(mockEventCommunicationPagerPresenter.loadPagerDataByEventId(exampleEventId)).thenReturn(
            exampleCommunicationPagerData
        )
        eventCommunicationPagerViewModel.observeStateAndEvents{ stateObserver, _ ->
            eventCommunicationPagerViewModel.loadRepresentedData(exampleEventId)
            stateObserver.assertObserved(Loading, DataLoaded(exampleCommunicationPagerData))
        }
    }

}