package hu.bme.aut.android.together.eventlist

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.google.common.truth.Truth
import hu.bme.aut.android.together.ui.screen.currentevents.presenter.PastEventListPresenter
import hu.bme.aut.android.together.ui.screen.currentevents.viewmodel.EventListLoaded
import hu.bme.aut.android.together.ui.screen.currentevents.viewmodel.Loading
import hu.bme.aut.android.together.ui.screen.currentevents.viewmodel.PastEventListViewModel
import hu.bme.aut.android.together.ui.model.EventShortInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class PastEventListViewModelTest : ViewModelTest() {

    private val pastEventListPresenterMock: PastEventListPresenter =
        Mockito.mock(PastEventListPresenter::class.java)

    private lateinit var pastEventListViewModel: PastEventListViewModel

    @Before
    fun setUp() {
        pastEventListViewModel = PastEventListViewModel(pastEventListPresenterMock)
    }

    @Test
    fun checkInitialViewStateIsLoadingState() {
        Truth.assertThat(pastEventListViewModel.state.value is Loading)
    }

    @Test
    fun checkViewStateAfterSuccessfulLoading(): Unit = runBlockingTest {
        val exampleId = 1L
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
        Mockito.`when`(pastEventListPresenterMock.loadPastEventShortInfoByProfileId(exampleId)).thenReturn(
            exampleEventShortInfoList
        )
        pastEventListViewModel.observeStateAndEvents { stateObserver, _ ->
            pastEventListViewModel.loadPastEventShortInfoListByProfileId(exampleId)
            stateObserver.assertObserved(
                Loading, EventListLoaded(
                    exampleEventShortInfoList
                )
            )
        }
    }
}