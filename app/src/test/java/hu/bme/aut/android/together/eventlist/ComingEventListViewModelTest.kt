package hu.bme.aut.android.together.eventlist

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.google.common.truth.Truth
import hu.bme.aut.android.together.features.currentevents.presenter.ComingEventListPresenter
import hu.bme.aut.android.together.features.currentevents.viewmodel.ComingEventListViewModel
import hu.bme.aut.android.together.features.currentevents.viewmodel.EventListLoaded
import hu.bme.aut.android.together.features.currentevents.viewmodel.Loading
import hu.bme.aut.android.together.model.presentation.EventShortInfo
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
class ComingEventListViewModelTest : ViewModelTest() {

    private val comingEventListPresenterMock: ComingEventListPresenter =
        Mockito.mock(ComingEventListPresenter::class.java)

    private lateinit var comingEventListViewModel: ComingEventListViewModel

    @Before
    fun setUp() {
        comingEventListViewModel = ComingEventListViewModel(comingEventListPresenterMock)
    }

    @Test
    fun checkInitialViewStateIsLoadingState() {
        Truth.assertThat(comingEventListViewModel.state.value is Loading)
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
        `when`(comingEventListPresenterMock.loadPastEventShortInfoByProfileId(exampleId)).thenReturn(
            exampleEventShortInfoList
        )
        comingEventListViewModel.observeStateAndEvents { stateObserver, _ ->
            comingEventListViewModel.loadComingEventShortInfoListByProfileId(exampleId)
            stateObserver.assertObserved(
                Loading, EventListLoaded(
                    exampleEventShortInfoList
                )
            )
        }
    }
}