package hu.bme.aut.android.together.eventcontrol.details

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.google.common.truth.Truth
import hu.bme.aut.android.together.features.eventcontrol.details.presenter.EventDetailsPresenter
import hu.bme.aut.android.together.features.eventcontrol.details.viewmodel.EventDetailsLoaded
import hu.bme.aut.android.together.features.eventcontrol.details.viewmodel.EventDetailsViewModel
import hu.bme.aut.android.together.features.eventcontrol.details.viewmodel.Loading
import hu.bme.aut.android.together.model.presentation.EventDetails
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
class EventDetailsViewModelTest : ViewModelTest() {

    private val eventDetailsPresenterMock = mock(EventDetailsPresenter::class.java)

    private lateinit var eventDetailsViewModel: EventDetailsViewModel

    @Before
    fun setUp() {
        eventDetailsViewModel = EventDetailsViewModel((eventDetailsPresenterMock))
    }

    @Test
    fun checkInitialLoadingState() {
        Truth.assertThat(eventDetailsViewModel.state.value is Loading)
    }

    @Test
    fun checkViewStateAfterSuccessfulLoading() : Unit = runBlockingTest {
        val exampleEventDetails = EventDetails(
            1L,
            "Kristóf's birtday party",
            "https://picsum.photos/200",
            "2021.4.20. 6:9",
            "2021.6.9. 4:20",
            "Budapest, Irinyi József u. 42.",
            "Lorem ipsum dolor sit amet!",
            false,
            0,
            0,
            isPrivate = true,
            isParticipant = true,
            isOrganiser = true
        )
        `when`(eventDetailsPresenterMock.getEventDetailsById(exampleEventDetails.eventId)).thenReturn(
            exampleEventDetails
        )
        eventDetailsViewModel.observeStateAndEvents { stateObserver, _ ->
            eventDetailsViewModel.loadEventDetails(exampleEventDetails.eventId)
            stateObserver.assertObserved(Loading, EventDetailsLoaded(exampleEventDetails))
        }
    }

}