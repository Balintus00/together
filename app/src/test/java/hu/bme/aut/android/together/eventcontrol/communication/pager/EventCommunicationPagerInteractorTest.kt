package hu.bme.aut.android.together.eventcontrol.communication.pager

import hu.bme.aut.android.together.features.eventcontrol.communication.pager.interactor.EventCommunicationPagerInteractor
import hu.bme.aut.android.together.model.domain.DomainCommunicationPagerData
import hu.bme.aut.android.together.network.NetworkDataSource
import hu.bme.aut.android.together.persistence.repository.EventCommunicationPagerDataRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import hu.bme.aut.android.together.mockito.any

@RunWith(JUnit4::class)
class EventCommunicationPagerInteractorTest {

    private val mockNetworkDataSource: NetworkDataSource =
        mock(NetworkDataSource::class.java)

    private val mockRepository: EventCommunicationPagerDataRepository =
        mock(EventCommunicationPagerDataRepository::class.java)

    private lateinit var eventCommunicationPagerInteractor: EventCommunicationPagerInteractor

    @Before
    fun setUp() {
        eventCommunicationPagerInteractor =
            EventCommunicationPagerInteractor(mockNetworkDataSource, mockRepository)
    }

    @Test
    fun checkCachingIfNetworkWasAvailable() {
        val exampleDomainCommunicationPagerData = DomainCommunicationPagerData(
            1L, "Lorem", true, 1
        )
        `when`(
            mockNetworkDataSource.getCommunicationPagerDataById(
                exampleDomainCommunicationPagerData.id
            )
        ).thenReturn(
            exampleDomainCommunicationPagerData
        )
        eventCommunicationPagerInteractor.loadPagerDataByEventId(exampleDomainCommunicationPagerData.id)
        verify(
            mockNetworkDataSource, times(1)
        ).getCommunicationPagerDataById(
            exampleDomainCommunicationPagerData.id
        )
        verify(
            mockRepository, times(1)
        ).persistCommunicationPagerData(
            exampleDomainCommunicationPagerData
        )
        verify(
            mockRepository, never()
        ).loadCommunicationPagerDataById(
            exampleDomainCommunicationPagerData.id
        )
    }

    @Test
    fun checkCachingIfNetworkWasUnavailable() {
        val exampleDomainCommunicationPagerData = DomainCommunicationPagerData(
            1L, "Lorem", true, 1
        )
        `when`(
            mockNetworkDataSource.getCommunicationPagerDataById(
                exampleDomainCommunicationPagerData.id
            )
        ).thenReturn(
            null
        )
        `when`(
            mockRepository.loadCommunicationPagerDataById(
                exampleDomainCommunicationPagerData.id
            )
        ).thenReturn(
            exampleDomainCommunicationPagerData
        )
        eventCommunicationPagerInteractor.loadPagerDataByEventId(exampleDomainCommunicationPagerData.id)
        verify(
            mockNetworkDataSource, times(1)
        ).getCommunicationPagerDataById(
            exampleDomainCommunicationPagerData.id
        )
        verify(
            mockRepository, never()
        ).persistCommunicationPagerData(
            any(DomainCommunicationPagerData::class.java)
        )
        verify(
            mockRepository, times(1)
        ).loadCommunicationPagerDataById(
            exampleDomainCommunicationPagerData.id
        )
    }

}