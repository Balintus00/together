package hu.bme.aut.android.together.domain.interactor

import hu.bme.aut.android.together.ui.screen.eventcontrol.details.interactor.EventDetailsInteractor
import hu.bme.aut.android.together.domain.model.DomainEventDetails
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventDetailsRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import java.text.SimpleDateFormat
import java.util.*
import hu.bme.aut.android.together.mockito.any

@RunWith(JUnit4::class)
class EventDetailsInteractorTest {

    private val mockNetworkDataSource: NetworkDataSource =
        mock(NetworkDataSource::class.java)

    private val mockEventDetailsRepository: EventDetailsRepository =
        mock(EventDetailsRepository::class.java)

    private lateinit var eventDetailsInteractor: EventDetailsInteractor

    @Before
    fun setUp() {
        eventDetailsInteractor =
            EventDetailsInteractor(mockNetworkDataSource, mockEventDetailsRepository)
    }

    @Test
    fun checkCachingIfNetworkWasAvailable() {
        val exampleDomainEventDetails = DomainEventDetails(
            1L,
            "Kristóf's birtday party",
            "https://picsum.photos/200",
            "Tech",
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("2020.02.14. 16:00") }!!,
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("2020.02.14. 22:00") }!!,
            "Budapest, Irinyi József u. 42.",
            "Lorem ipsum dolor sit amet!",
            false,
            0,
            0,
            isPrivate = true,
            isParticipant = true,
            isOrganiser = true
        )
        `when`(mockNetworkDataSource.getEventDetailsById(exampleDomainEventDetails.id)).thenReturn(
            exampleDomainEventDetails
        )
        eventDetailsInteractor.getEventDetailsByEventId(exampleDomainEventDetails.id)
        verify(mockNetworkDataSource, times(1)).getEventDetailsById(exampleDomainEventDetails.id)
        verify(mockEventDetailsRepository, times(1)).saveEventDetails(exampleDomainEventDetails)
        verify(mockEventDetailsRepository, never()).loadEventDetailsById(anyLong())
    }

    @Test
    fun checkCachingIfNetworkWasUnavailable() {
        val exampleDomainEventDetails = DomainEventDetails(
            1L,
            "Kristóf's birtday party",
            "https://picsum.photos/200",
            "Tech",
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("2020.02.14. 16:00") }!!,
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("2020.02.14. 22:00") }!!,
            "Budapest, Irinyi József u. 42.",
            "Lorem ipsum dolor sit amet!",
            false,
            0,
            0,
            isPrivate = true,
            isParticipant = true,
            isOrganiser = true
        )
        `when`(mockNetworkDataSource.getEventDetailsById(exampleDomainEventDetails.id)).thenReturn(
            null
        )
        `when`(mockEventDetailsRepository.loadEventDetailsById(exampleDomainEventDetails.id)).thenReturn(
            exampleDomainEventDetails
        )
        eventDetailsInteractor.getEventDetailsByEventId(exampleDomainEventDetails.id)
        verify(mockNetworkDataSource, times(1)).getEventDetailsById(exampleDomainEventDetails.id)
        verify(
            mockEventDetailsRepository,
            never()
        ).saveEventDetails(any(DomainEventDetails::class.java))
        verify(
            mockEventDetailsRepository,
            times(1)
        ).loadEventDetailsById(exampleDomainEventDetails.id)
    }

}