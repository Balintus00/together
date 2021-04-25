package hu.bme.aut.android.together.eventcontrol.wholedescription

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.google.common.truth.Truth
import hu.bme.aut.android.together.features.eventcontrol.wholedescription.presenter.EventWholeDescriptionPresenter
import hu.bme.aut.android.together.features.eventcontrol.wholedescription.viewmodel.EventWholeDescriptionLoaded
import hu.bme.aut.android.together.features.eventcontrol.wholedescription.viewmodel.EventWholeDescriptionViewModel
import hu.bme.aut.android.together.features.eventcontrol.wholedescription.viewmodel.Loading
import hu.bme.aut.android.together.model.presentation.EventDescriptionScreenData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class EventWholeDescriptionViewModelTest : ViewModelTest() {

    private val eventWholeDescriptionPresenterMock =
        mock(EventWholeDescriptionPresenter::class.java)

    private lateinit var eventWholeDescriptionViewModel: EventWholeDescriptionViewModel

    @Before
    fun setUp() {
        eventWholeDescriptionViewModel =
            EventWholeDescriptionViewModel(eventWholeDescriptionPresenterMock)
    }

    @Test
    fun checkInitialLoadingState() {
        Truth.assertThat(eventWholeDescriptionViewModel.state.value is Loading)
    }

    @Test
    fun checkViewStateAfterSuccessfulLoading(): Unit = runBlockingTest {
        val exampleEventId = 3L
        val exampleWholeDescriptionData = EventDescriptionScreenData(
            "Going to gym",
            "2020.02.14. 16:00",
            "2020.02.14. 22:00",
            "Budapest, Irinyi József u. 42.",
            "Lorem ipsum dolor sit amet."
        )
        Mockito.`when`(eventWholeDescriptionPresenterMock.getEventDescriptionScreenData(exampleEventId)).thenReturn(
            exampleWholeDescriptionData
        )
        eventWholeDescriptionViewModel.observeStateAndEvents { stateObserver, _ ->
            eventWholeDescriptionViewModel.loadDescriptionScreenData(exampleEventId)
            stateObserver.assertObserved(Loading, EventWholeDescriptionLoaded(exampleWholeDescriptionData))
        }
    }

}