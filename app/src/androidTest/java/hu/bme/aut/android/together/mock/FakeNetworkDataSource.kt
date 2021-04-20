package hu.bme.aut.android.together.mock

import hu.bme.aut.android.together.model.domain.*
import hu.bme.aut.android.together.network.NetworkDataSource
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FakeNetworkDataSource @Inject constructor() : NetworkDataSource {

    companion object {
        const val usedDateString = "1999.9.1"

        const val organiserEventId = 1L
        const val participantEventId = 2L
        @Suppress("unused")
        const val nonParticipantEventId = 3L
        @Suppress("unused")
        const val privateEventId = 1L

        var usedProfileData = DomainProfileData(
            1,
            "Botond",
            "B0T0ND",
            SimpleDateFormat("yyyy.M.d", Locale.ENGLISH).run { parse(usedDateString) }!!,
            "https://picsum.photos/200",
            1
        )
    }

    override fun getUserProfileById(id: Long): DomainProfileData {
        return usedProfileData
    }

    override fun getIncomingInvitesById(id: Long): List<DomainEventInvitation> {
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
        return listOf(
            DomainEventShortInfo(
                1,
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
                2,
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
                3,
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
        return listOf(
            DomainEventShortInfo(
                1,
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
        return listOf(
            DomainEventShortInfo(
                1,
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
        return when(eventId) {
            organiserEventId -> {
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
            participantEventId -> {
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