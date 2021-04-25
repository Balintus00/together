package hu.bme.aut.android.together.eventcontrol.communication.newslist

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.google.common.truth.Truth
import hu.bme.aut.android.together.features.eventcontrol.communication.newslist.presenter.NewsListPresenter
import hu.bme.aut.android.together.features.eventcontrol.communication.newslist.viewmodel.Loading
import hu.bme.aut.android.together.features.eventcontrol.communication.newslist.viewmodel.NewsListLoaded
import hu.bme.aut.android.together.features.eventcontrol.communication.newslist.viewmodel.NewsListViewModel
import hu.bme.aut.android.together.model.presentation.EventNews
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
class NewsListViewModelTest : ViewModelTest() {

    private val mockNewsListPresenter = mock(NewsListPresenter::class.java)

    private lateinit var newsListViewModel: NewsListViewModel

    @Before
    fun setUp() {
        newsListViewModel = NewsListViewModel(mockNewsListPresenter)
    }

    @Test
    fun checkInitialLoadingState() {
        Truth.assertThat(newsListViewModel.state.value is Loading)
    }

    @Test
    fun checkViewStateAfterSuccessfulLoading() : Unit = runBlockingTest {
        val exampleEventId = 1L
        val exampleNewsList = listOf<EventNews>()
        `when`(mockNewsListPresenter.loadEventNews(exampleEventId)).thenReturn(
            exampleNewsList
        )
        newsListViewModel.observeStateAndEvents { stateObserver, _ ->
            newsListViewModel.loadEventNews(exampleEventId)
            stateObserver.assertObserved(Loading, NewsListLoaded(exampleNewsList))
        }
    }

}