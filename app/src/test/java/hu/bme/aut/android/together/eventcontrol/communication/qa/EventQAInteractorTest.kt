package hu.bme.aut.android.together.eventcontrol.communication.qa

import hu.bme.aut.android.together.features.eventcontrol.communication.qa.interactor.EventQAInteractor
import hu.bme.aut.android.together.model.domain.DomainEventQuestionAndAnswer
import hu.bme.aut.android.together.network.NetworkDataSource
import hu.bme.aut.android.together.persistence.repository.EventQuestionAndAnswerRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import hu.bme.aut.android.together.mockito.any

@RunWith(JUnit4::class)
class EventQAInteractorTest {

    private val mockNetworkDataSource: NetworkDataSource =
        mock(NetworkDataSource::class.java)

    private val mockRepository: EventQuestionAndAnswerRepository =
        mock(EventQuestionAndAnswerRepository::class.java)

    private lateinit var eventQAInteractor: EventQAInteractor

    @Before
    fun setUp() {
        eventQAInteractor = EventQAInteractor(mockNetworkDataSource, mockRepository)
    }

    @Test
    fun checkCachingIfNetworkWasAvailable() {
        val exampleId = 1L
        val exampleQAList = listOf<DomainEventQuestionAndAnswer>()
        `when`(mockNetworkDataSource.getEventQuestionsAndAnswersByEventId(exampleId)).thenReturn(
            exampleQAList
        )
        eventQAInteractor.getEventQuestionsAndAnswersById(exampleId)
        verify(mockNetworkDataSource, times(1)).getEventQuestionsAndAnswersByEventId(exampleId)
        verify(mockRepository, times(1)).persistQuestionAndAnswer(exampleQAList)
        verify(mockRepository, never()).loadCachedQuestionsAndAnswersByEventId(anyLong())
    }

    @Test
    fun checkCachingIfNetworkWasUnavailable() {
        val exampleId = 1L
        val exampleQAList = listOf<DomainEventQuestionAndAnswer>()
        `when`(mockNetworkDataSource.getEventQuestionsAndAnswersByEventId(exampleId)).thenReturn(
            null
        )
        `when`(mockRepository.loadCachedQuestionsAndAnswersByEventId(exampleId)).thenReturn(
            exampleQAList
        )
        eventQAInteractor.getEventQuestionsAndAnswersById(exampleId)
        verify(mockNetworkDataSource, times(1)).getEventQuestionsAndAnswersByEventId(exampleId)
        verify(mockRepository, never()).persistQuestionAndAnswer(any())
        verify(mockRepository, times(1)).loadCachedQuestionsAndAnswersByEventId(exampleId)
    }

}