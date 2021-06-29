package hu.bme.aut.android.together.eventcontrol.communication.newslist

import hu.bme.aut.android.together.domain.interactor.NewsListInteractor
import hu.bme.aut.android.together.domain.model.DomainEventNews
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventNewsRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import hu.bme.aut.android.together.mockito.any

@RunWith(JUnit4::class)
class NewsListInteractorTest {

    private val mockNetworkDataSource: NetworkDataSource =
        mock(NetworkDataSource::class.java)

    private val mockRepository: EventNewsRepository =
        mock(EventNewsRepository::class.java)

    private lateinit var newsListInteractor: NewsListInteractor

    @Before
    fun setUp() {
        newsListInteractor = NewsListInteractor(mockNetworkDataSource, mockRepository)
    }

    @Test
    fun checkCachingIfNetworkWasAvailable() {
        val exampleId = 1L
        val exampleNewsList = listOf<DomainEventNews>()
        `when`(mockNetworkDataSource.getEventNewsById(exampleId)).thenReturn(
            exampleNewsList
        )
        newsListInteractor.loadEventNews(exampleId)
        verify(mockNetworkDataSource, times(1)).getEventNewsById(exampleId)
        verify(mockRepository, times(1)).persistEventNews(exampleId, exampleNewsList)
        verify(mockRepository, never()).loadPersistedEventNewsByEventId(exampleId)
    }

    @Test
    fun checkCachingIfNetworkWasUnavailable() {
        val exampleId = 1L
        val exampleNewsList = listOf<DomainEventNews>()
        `when`(mockNetworkDataSource.getEventNewsById(exampleId)).thenReturn(
            null
        )
        `when`(mockRepository.loadPersistedEventNewsByEventId(exampleId)).thenReturn(
            exampleNewsList
        )
        newsListInteractor.loadEventNews(exampleId)
        verify(mockNetworkDataSource, times(1)).getEventNewsById(exampleId)
        verify(mockRepository, never()).persistEventNews(anyLong(), any())
        verify(mockRepository, times(1)).loadPersistedEventNewsByEventId(exampleId)
    }

}