package hu.bme.aut.android.together.eventcontrol.wholedescription

import hu.bme.aut.android.together.features.eventcontrol.wholedescription.interactor.EventWholeDescriptionInteractor
import hu.bme.aut.android.together.mockito.any
import hu.bme.aut.android.together.model.domain.DomainEventDescriptionData
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventDescriptionDataRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import java.text.SimpleDateFormat
import java.util.*

@RunWith(JUnit4::class)
class EventWholeDescriptionInteractorTest {

    private val mockNetworkDataSource: NetworkDataSource =
        Mockito.mock(NetworkDataSource::class.java)

    private val mockEventDescriptionDataRepository: EventDescriptionDataRepository =
        Mockito.mock(EventDescriptionDataRepository::class.java)

    private lateinit var eventWholeDescriptionInteractor: EventWholeDescriptionInteractor

    @Before
    fun setUp() {
        eventWholeDescriptionInteractor = EventWholeDescriptionInteractor(
            mockNetworkDataSource,
            mockEventDescriptionDataRepository
        )
    }

    @Test
    fun checkCachingIfNetworkWasAvailable() {
        val exampleDomainEventDescriptionData = DomainEventDescriptionData(
            3L,
            "Going to gym",
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("2020.02.14. 16:00") }!!,
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("2020.02.14. 22:00") }!!,
            "Budapest, Irinyi József u. 42.",
            "Lorem ipsum dolor sit amet."
        )
        Mockito.`when`(mockNetworkDataSource.getEventDescriptionDataById(exampleDomainEventDescriptionData.id)).thenReturn(
            exampleDomainEventDescriptionData
        )
        eventWholeDescriptionInteractor.getEventDescriptionDataByEventId(exampleDomainEventDescriptionData.id)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1)).getEventDescriptionDataById(exampleDomainEventDescriptionData.id)
        Mockito.verify(mockEventDescriptionDataRepository, Mockito.times(1)).saveEventDescriptionData(exampleDomainEventDescriptionData)
        Mockito.verify(mockEventDescriptionDataRepository, Mockito.never())
            .loadEventDescriptionData(exampleDomainEventDescriptionData.id)
    }

    @Test
    fun checkCachingIfNetworkWasUnavailable() {
        val exampleDomainEventDescriptionData = DomainEventDescriptionData(
            3L,
            "Going to gym",
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("2020.02.14. 16:00") }!!,
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("2020.02.14. 22:00") }!!,
            "Budapest, Irinyi József u. 42.",
            "Lorem ipsum dolor sit amet."
        )
        Mockito.`when`(mockNetworkDataSource.getEventDescriptionDataById(exampleDomainEventDescriptionData.id)).thenReturn(
            null
        )
        Mockito.`when`(mockEventDescriptionDataRepository.loadEventDescriptionData(exampleDomainEventDescriptionData.id)).thenReturn(
            exampleDomainEventDescriptionData
        )
        eventWholeDescriptionInteractor.getEventDescriptionDataByEventId(exampleDomainEventDescriptionData.id)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1)).getEventDescriptionDataById(exampleDomainEventDescriptionData.id)
        Mockito.verify(mockEventDescriptionDataRepository, Mockito.never()).saveEventDescriptionData(any(DomainEventDescriptionData::class.java))
        Mockito.verify(mockEventDescriptionDataRepository, Mockito.times(1))
            .loadEventDescriptionData(exampleDomainEventDescriptionData.id)
    }

}