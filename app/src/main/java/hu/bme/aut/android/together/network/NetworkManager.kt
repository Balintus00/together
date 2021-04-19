package hu.bme.aut.android.together.network

import hu.bme.aut.android.together.model.domain.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

//TODO this fake implementation will be changed to communicate with the backend
class NetworkManager @Inject constructor() : NetworkDataSource {

    companion object {
        private const val SIMULATED_LOADING_TIME_MS = 500L
    }

    //TODO: RedundantNullabeReturnType warning is suppressed, because it will be fixed
    // in the actual implementation later

    @Suppress("RedundantNullableReturnType")
    override fun getUserProfileById(@Suppress("UNUSED_PARAMETER") id: Long): DomainProfileData? {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return DomainProfileData(
            1,
            "Botond",
            "B0T0ND",
            SimpleDateFormat("yyyy.MM.d.", Locale.ENGLISH).run { parse("1999.09.01.") }!!,
            "https://picsum.photos/200",
            1
        )
    }

    @Suppress("RedundantNullableReturnType")
    override fun getIncomingInvitesById(@Suppress("UNUSED_PARAMETER") id: Long): List<DomainEventInvitation>? {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return listOf(
            DomainEventInvitation(
                1,
                "Come join my birthday party!",
                "KR1ST0F",
                "Lorem ipsum dolor sit amet!"
            )
        )
    }

    override fun getComingEventShortInfoListByProfileId(profileId: Long): List<DomainEventShortInfo> {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return listOf(
            DomainEventShortInfo(
                1L,
                "Kristóf's birthday party",
                "Budapest",
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2022.03.02. 16:00") }!!,
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2022.03.02. 22:00") }!!,
                "https://picsum.photos/200"
                ),
            DomainEventShortInfo(
                2L,
                "Budapest sightseeing tour",
                "Budapest",
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2022.03.02. 16:00") }!!,
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2022.03.02. 22:00") }!!,
                "https://picsum.photos/200"
            ),
            DomainEventShortInfo(
                3L,
                "Going to gym",
                "Budapest",
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2121.03.02. 16:00") }!!,
                SimpleDateFormat(
                    "yyyy.MM.dd. HH:mm",
                    Locale.ENGLISH
                ).run { parse("2022.03.02. 22:00") }!!,
                "https://picsum.photos/200"
            )
        )
    }

    override fun getPastEventShortInfoListByProfileId(profileId: Long): List<DomainEventShortInfo> {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return listOf(
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
    }

    override fun searchEventsByQueryParameter(queryParameter: DomainEventQueryParameter): List<DomainEventShortInfo> {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return listOf(
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
    }

    override fun getEventDetailsById(eventId: Long): DomainEventDetails {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        //returning different kinds of events by id
        return when(eventId) {
            1L -> {
                DomainEventDetails(
                    1L,
                    "Kristóf's birtday party",
                    "https://picsum.photos/200",
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
            }
            2L -> {
                DomainEventDetails(
                    2L,
                    "Budapest sightseeing tour",
                    "https://picsum.photos/200",
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
                    true,
                    50,
                    30,
                    isPrivate = false,
                    isParticipant = true,
                    isOrganiser = false
                )
            }
            else -> {
                DomainEventDetails(
                    3L,
                    "Going to gym",
                    "https://picsum.photos/200",
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
                    isPrivate = false,
                    isParticipant = false,
                    isOrganiser = false
                )
            }
        }
    }

}