package hu.bme.aut.android.together.searchevent.result


import hu.bme.aut.android.together.domain.interactor.EventSearchResultInteractor
import hu.bme.aut.android.together.mockito.any
import hu.bme.aut.android.together.domain.model.DomainEventQueryParameter
import hu.bme.aut.android.together.domain.model.DomainEventShortInfo
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventShortInfoRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import java.text.SimpleDateFormat
import java.util.*

@RunWith(JUnit4::class)
class EventSearchResultInteractorTest {

    private val mockNetworkDataSource: NetworkDataSource =
        Mockito.mock(NetworkDataSource::class.java)

    private val mockEventShortInfoRepository: EventShortInfoRepository =
        Mockito.mock(EventShortInfoRepository::class.java)

    private lateinit var eventSearchResultInteractor: EventSearchResultInteractor

    @Before
    fun setUp() {
        eventSearchResultInteractor =
            EventSearchResultInteractor(mockNetworkDataSource, mockEventShortInfoRepository)
    }

    @Test
    fun testCachingLogicIfNetworkIsAvailable() {
        val exampleQueryParameter = DomainEventQueryParameter(
            "",
            "Szombathely",
            0,
            Date(),
            Date(),
            "Family"
        )
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
                "https://picsum.photos/200"
            )
        )
        Mockito.`when`(mockNetworkDataSource.searchEventsByQueryParameter(exampleQueryParameter))
            .thenReturn(
                exampleEventShortInfoList
            )
        eventSearchResultInteractor.loadResultsByQueryParameters(exampleQueryParameter)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1))
            .searchEventsByQueryParameter(exampleQueryParameter)
        Mockito.verify(mockEventShortInfoRepository, Mockito.times(1))
            .persistResultEventShortInfo(*exampleEventShortInfoList.toTypedArray())
        Mockito.verify(mockEventShortInfoRepository, Mockito.never())
            .loadCachedResultEventShortInfo()
    }

    @Test
    fun testCachingLogicIfNetworkIsUnAvailable() {
        val exampleQueryParameter = DomainEventQueryParameter(
            "",
            "Szombathely",
            0,
            Date(),
            Date(),
            "Family"
        )
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
                "https://picsum.photos/200"
            )
        )
        Mockito.`when`(mockNetworkDataSource.searchEventsByQueryParameter(exampleQueryParameter))
            .thenReturn(null)
        Mockito.`when`(mockEventShortInfoRepository.loadCachedResultEventShortInfo())
            .thenReturn(exampleEventShortInfoList)
        eventSearchResultInteractor.loadResultsByQueryParameters(exampleQueryParameter)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1))
            .searchEventsByQueryParameter(exampleQueryParameter)
        Mockito.verify(mockEventShortInfoRepository, Mockito.never())
            .persistResultEventShortInfo(any())
        Mockito.verify(mockEventShortInfoRepository, Mockito.times(1))
            .loadCachedResultEventShortInfo()
    }

}