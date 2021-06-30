package hu.bme.aut.android.together.ui.screen.searchevent.result.viewmodel

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.google.common.truth.Truth
import hu.bme.aut.android.together.ui.screen.searchevent.result.presenter.EventSearchResultPresenter
import hu.bme.aut.android.together.ui.model.EventQueryParameter
import hu.bme.aut.android.together.ui.model.EventShortInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class EventSearchResultViewModelTest : ViewModelTest() {

    private val eventSearchResultPresenterMock : EventSearchResultPresenter =
        Mockito.mock(EventSearchResultPresenter::class.java)

    private lateinit var eventSearchResultViewModel : EventSearchResultViewModel

    @Before
    fun setUp() {
        eventSearchResultViewModel = EventSearchResultViewModel(eventSearchResultPresenterMock)
    }

    @Test
    fun checkInitialViewStateIsLoadingState() {
        Truth.assertThat(eventSearchResultViewModel.state.value is Loading)
    }

    @Test
    fun checkViewStateAfterSuccessfulLoading(): Unit = runBlockingTest {
        val exampleQueryParameter = EventQueryParameter(
            "",
            "Szombathely",
            0,
            "2021.03.18.",
            "18:53",
            "2021.04.18.",
            "18:54",
            "Family"
        )
        val exampleEventShortInfoList = listOf(
            EventShortInfo(
                1L,
                "Coronavirus beginning party",
                "Budapest",
                "2020.02.14. 16:00",
                "2020.02.14. 22:00",
                "https://picsum.photos/200"
            )
        )
        `when`(eventSearchResultPresenterMock.loadSearchResults(exampleQueryParameter)).thenReturn(
            exampleEventShortInfoList
        )
        eventSearchResultViewModel.observeStateAndEvents { stateObserver, _ ->
            eventSearchResultViewModel.loadResults(exampleQueryParameter)
            stateObserver.assertObserved(
                Loading, ResultsLoaded(exampleEventShortInfoList)
            )
        }
    }

}