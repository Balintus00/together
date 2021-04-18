package hu.bme.aut.android.together.eventlist

import hu.bme.aut.android.together.features.currentevents.interactor.EventListInteractor
import hu.bme.aut.android.together.mockito.any
import hu.bme.aut.android.together.model.domain.DomainEventShortInfo
import hu.bme.aut.android.together.network.NetworkDataSource
import hu.bme.aut.android.together.persistence.repository.EventShortInfoRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import java.text.SimpleDateFormat
import java.util.*

@RunWith(JUnit4::class)
class EventListInteractorTest {

    private val mockNetworkDataSource: NetworkDataSource =
        Mockito.mock(NetworkDataSource::class.java)

    private val mockEventShortInfoRepository: EventShortInfoRepository =
        Mockito.mock(EventShortInfoRepository::class.java)

    private lateinit var eventListInteractor: EventListInteractor

    @Before
    fun setUp() {
        eventListInteractor = EventListInteractor(
            mockNetworkDataSource, mockEventShortInfoRepository
        )
    }

    @Test
    fun checkCachingIfNetworkWasAvailableWhenLoadingIncomingEvents() {
        val exampleProfileId = 1L
        val exampleEventShortInfoList = listOf(
            DomainEventShortInfo(
                1L,
                "Coronavirus beginning party",
                "Budapest",
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2020.02.14. 16:00") }!!,
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2020.02.14. 22:00") }!!,
                "https://picsum.photos/200",
                true
            )
        )
        Mockito.`when`(mockNetworkDataSource.getComingEventShortInfoListByProfileId(exampleProfileId))
            .thenReturn(
                exampleEventShortInfoList
            )
        eventListInteractor.getComingEventShortInfoByProfileId(exampleProfileId)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1))
            .getComingEventShortInfoListByProfileId(exampleProfileId)
        Mockito.verify(mockEventShortInfoRepository, Mockito.times(1))
            .persistComingEventShortInfo(*exampleEventShortInfoList.toTypedArray())
        Mockito.verify(mockEventShortInfoRepository, Mockito.never())
            .loadCachedComingEventShortInfo()
    }

    @Test
    fun checkCachingIfNetworkWasUnavailableWhenLoadingIncomingEvents() {
        val exampleProfileId = 1L
        val exampleEventShortInfoList = listOf(
            DomainEventShortInfo(
                1L,
                "Coronavirus beginning party",
                "Budapest",
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2020.02.14. 16:00") }!!,
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2020.02.14. 22:00") }!!,
                "https://picsum.photos/200",
                true
            )
        )
        Mockito.`when`(mockNetworkDataSource.getComingEventShortInfoListByProfileId(exampleProfileId))
            .thenReturn(null)
        Mockito.`when`(mockEventShortInfoRepository.loadCachedComingEventShortInfo())
            .thenReturn(exampleEventShortInfoList)
        eventListInteractor.getComingEventShortInfoByProfileId(exampleProfileId)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1))
            .getComingEventShortInfoListByProfileId(1L)
        Mockito.verify(mockEventShortInfoRepository, Mockito.never())
            .persistComingEventShortInfo(any())
        Mockito.verify(mockEventShortInfoRepository, Mockito.times(1))
            .loadCachedComingEventShortInfo()
    }

    @Test
    fun checkCachingIfNetworkWasAvailableWhenLoadingPastEvents() {
        val exampleProfileId = 1L
        val exampleEventShortInfoList = listOf(
            DomainEventShortInfo(
                1L,
                "Coronavirus beginning party",
                "Budapest",
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2020.02.14. 16:00") }!!,
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2020.02.14. 22:00") }!!,
                "https://picsum.photos/200",
                false
            )
        )
        Mockito.`when`(mockNetworkDataSource.getPastEventShortInfoListByProfileId(exampleProfileId))
            .thenReturn(
                exampleEventShortInfoList
            )
        eventListInteractor.getPastEventShortInfoByProfileId(exampleProfileId)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1))
            .getPastEventShortInfoListByProfileId(exampleProfileId)
        Mockito.verify(mockEventShortInfoRepository, Mockito.times(1))
            .persistPastEventShortInfo(*exampleEventShortInfoList.toTypedArray())
        Mockito.verify(mockEventShortInfoRepository, Mockito.never())
            .loadCachedPastEventShortInfo()
    }

    @Test
    fun checkCachingIfNetworkWasUnavailableWhenLoadingPastEvents() {
        val exampleProfileId = 1L
        val exampleEventShortInfoList = listOf(
            DomainEventShortInfo(
                1L,
                "Coronavirus beginning party",
                "Budapest",
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2020.02.14. 16:00") }!!,
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2020.02.14. 22:00") }!!,
                "https://picsum.photos/200",
                false
            )
        )
        Mockito.`when`(mockNetworkDataSource.getPastEventShortInfoListByProfileId(exampleProfileId))
            .thenReturn(null)
        Mockito.`when`(mockEventShortInfoRepository.loadCachedPastEventShortInfo())
            .thenReturn(exampleEventShortInfoList)
        eventListInteractor.getPastEventShortInfoByProfileId(exampleProfileId)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1))
            .getPastEventShortInfoListByProfileId(1L)
        Mockito.verify(mockEventShortInfoRepository, Mockito.never())
            .persistPastEventShortInfo(any())
        Mockito.verify(mockEventShortInfoRepository, Mockito.times(1))
            .loadCachedPastEventShortInfo()
    }

}