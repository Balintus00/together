package hu.bme.aut.android.together.mock

import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.domain.model.*
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

        var usedProfileData = DomainUserProfile(
            1,
            "Botond",
            "B0T0ND",
            SimpleDateFormat("yyyy.M.d", Locale.ENGLISH).run { parse(usedDateString) }!!,
            "https://picsum.photos/200",
            1
        )
    }

    override fun getUserProfileById(id: Long): DomainUserProfile {
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
        return when (eventId) {
            organiserEventId -> {
                DomainEventDetails(
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
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eget lobortis enim, ut convallis est. Curabitur sit amet ligula a orci elementum vulputate. Proin arcu lectus, volutpat eget aliquet nec, condimentum quis justo. Pellentesque tempor mauris quis tellus suscipit finibus. Aliquam at lobortis sapien, non iaculis mauris. In malesuada facilisis leo id cursus. Cras id risus fringilla, lacinia eros at, volutpat arcu. Etiam elementum sagittis pharetra. Praesent pretium elementum quam vel semper. In mollis, neque et efficitur blandit, magna nisl porta velit, eget elementum eros elit sed nisl. Sed consectetur vitae risus condimentum sollicitudin. Aliquam ultricies rhoncus justo sed semper. Sed in lorem porttitor, tincidunt ipsum sit amet, accumsan ipsum. Nam ac mattis nunc. Vivamus efficitur, tortor ac pulvinar auctor, enim nunc cursus odio, id tristique risus turpis eu nisi. Nunc luctus, mauris non tincidunt maximus, erat mi ultricies ligula, nec pretium nibh tellus a diam. Vestibulum blandit, neque sit amet varius convallis, diam ante venenatis ipsum, vitae gravida enim leo ac diam. Sed sapien sapien, pellentesque eu massa et, tempus placerat urna. Curabitur egestas, purus ac lobortis dignissim, ligula orci venenatis eros, non fermentum lectus tellus non leo. Etiam in accumsan quam. Nam vel ligula leo. Aenean in congue dolor. Sed dictum blandit velit vitae ultrices. Suspendisse nec ex vitae magna vehicula facilisis vel id justo. Nullam a semper erat. Sed venenatis ligula vel dignissim auctor. Quisque felis libero, aliquet vel diam a, rhoncus finibus ex.",
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
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eget lobortis enim, ut convallis est. Curabitur sit amet ligula a orci elementum vulputate. Proin arcu lectus, volutpat eget aliquet nec, condimentum quis justo. Pellentesque tempor mauris quis tellus suscipit finibus. Aliquam at lobortis sapien, non iaculis mauris. In malesuada facilisis leo id cursus. Cras id risus fringilla, lacinia eros at, volutpat arcu. Etiam elementum sagittis pharetra. Praesent pretium elementum quam vel semper. In mollis, neque et efficitur blandit, magna nisl porta velit, eget elementum eros elit sed nisl. Sed consectetur vitae risus condimentum sollicitudin. Aliquam ultricies rhoncus justo sed semper. Sed in lorem porttitor, tincidunt ipsum sit amet, accumsan ipsum. Nam ac mattis nunc. Vivamus efficitur, tortor ac pulvinar auctor, enim nunc cursus odio, id tristique risus turpis eu nisi. Nunc luctus, mauris non tincidunt maximus, erat mi ultricies ligula, nec pretium nibh tellus a diam. Vestibulum blandit, neque sit amet varius convallis, diam ante venenatis ipsum, vitae gravida enim leo ac diam. Sed sapien sapien, pellentesque eu massa et, tempus placerat urna. Curabitur egestas, purus ac lobortis dignissim, ligula orci venenatis eros, non fermentum lectus tellus non leo. Etiam in accumsan quam. Nam vel ligula leo. Aenean in congue dolor. Sed dictum blandit velit vitae ultrices. Suspendisse nec ex vitae magna vehicula facilisis vel id justo. Nullam a semper erat. Sed venenatis ligula vel dignissim auctor. Quisque felis libero, aliquet vel diam a, rhoncus finibus ex.",
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
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eget lobortis enim, ut convallis est. Curabitur sit amet ligula a orci elementum vulputate. Proin arcu lectus, volutpat eget aliquet nec, condimentum quis justo. Pellentesque tempor mauris quis tellus suscipit finibus. Aliquam at lobortis sapien, non iaculis mauris. In malesuada facilisis leo id cursus. Cras id risus fringilla, lacinia eros at, volutpat arcu. Etiam elementum sagittis pharetra. Praesent pretium elementum quam vel semper. In mollis, neque et efficitur blandit, magna nisl porta velit, eget elementum eros elit sed nisl. Sed consectetur vitae risus condimentum sollicitudin. Aliquam ultricies rhoncus justo sed semper. Sed in lorem porttitor, tincidunt ipsum sit amet, accumsan ipsum. Nam ac mattis nunc. Vivamus efficitur, tortor ac pulvinar auctor, enim nunc cursus odio, id tristique risus turpis eu nisi. Nunc luctus, mauris non tincidunt maximus, erat mi ultricies ligula, nec pretium nibh tellus a diam. Vestibulum blandit, neque sit amet varius convallis, diam ante venenatis ipsum, vitae gravida enim leo ac diam. Sed sapien sapien, pellentesque eu massa et, tempus placerat urna. Curabitur egestas, purus ac lobortis dignissim, ligula orci venenatis eros, non fermentum lectus tellus non leo. Etiam in accumsan quam. Nam vel ligula leo. Aenean in congue dolor. Sed dictum blandit velit vitae ultrices. Suspendisse nec ex vitae magna vehicula facilisis vel id justo. Nullam a semper erat. Sed venenatis ligula vel dignissim auctor. Quisque felis libero, aliquet vel diam a, rhoncus finibus ex.",
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

    override fun getEventDescriptionDataById(eventId: Long): DomainEventDescriptionData {
        return when (eventId) {
            1L -> {
                DomainEventDescriptionData(
                    1L,
                    "Kristóf's birthday party",
                    SimpleDateFormat(
                        "yyyy.MM.dd. HH:mm",
                        Locale.ENGLISH
                    ).run { parse("2020.02.14. 16:00") }!!,
                    SimpleDateFormat(
                        "yyyy.MM.dd. HH:mm",
                        Locale.ENGLISH
                    ).run { parse("2020.02.14. 22:00") }!!,
                    "Budapest, Irinyi József u. 42.",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eget lobortis enim, ut convallis est. Curabitur sit amet ligula a orci elementum vulputate. Proin arcu lectus, volutpat eget aliquet nec, condimentum quis justo. Pellentesque tempor mauris quis tellus suscipit finibus. Aliquam at lobortis sapien, non iaculis mauris. In malesuada facilisis leo id cursus. Cras id risus fringilla, lacinia eros at, volutpat arcu. Etiam elementum sagittis pharetra. Praesent pretium elementum quam vel semper. In mollis, neque et efficitur blandit, magna nisl porta velit, eget elementum eros elit sed nisl. Sed consectetur vitae risus condimentum sollicitudin. Aliquam ultricies rhoncus justo sed semper. Sed in lorem porttitor, tincidunt ipsum sit amet, accumsan ipsum. Nam ac mattis nunc. Vivamus efficitur, tortor ac pulvinar auctor, enim nunc cursus odio, id tristique risus turpis eu nisi. Nunc luctus, mauris non tincidunt maximus, erat mi ultricies ligula, nec pretium nibh tellus a diam. Vestibulum blandit, neque sit amet varius convallis, diam ante venenatis ipsum, vitae gravida enim leo ac diam. Sed sapien sapien, pellentesque eu massa et, tempus placerat urna. Curabitur egestas, purus ac lobortis dignissim, ligula orci venenatis eros, non fermentum lectus tellus non leo. Etiam in accumsan quam. Nam vel ligula leo. Aenean in congue dolor. Sed dictum blandit velit vitae ultrices. Suspendisse nec ex vitae magna vehicula facilisis vel id justo. Nullam a semper erat. Sed venenatis ligula vel dignissim auctor. Quisque felis libero, aliquet vel diam a, rhoncus finibus ex."
                )
            }
            2L -> {
                DomainEventDescriptionData(
                    2L,
                    "Budapest sightseeing tour",
                    SimpleDateFormat(
                        "yyyy.MM.dd. HH:mm",
                        Locale.ENGLISH
                    ).run { parse("2020.02.14. 16:00") }!!,
                    SimpleDateFormat(
                        "yyyy.MM.dd. HH:mm",
                        Locale.ENGLISH
                    ).run { parse("2020.02.14. 22:00") }!!,
                    "Budapest, Irinyi József u. 42.",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eget lobortis enim, ut convallis est. Curabitur sit amet ligula a orci elementum vulputate. Proin arcu lectus, volutpat eget aliquet nec, condimentum quis justo. Pellentesque tempor mauris quis tellus suscipit finibus. Aliquam at lobortis sapien, non iaculis mauris. In malesuada facilisis leo id cursus. Cras id risus fringilla, lacinia eros at, volutpat arcu. Etiam elementum sagittis pharetra. Praesent pretium elementum quam vel semper. In mollis, neque et efficitur blandit, magna nisl porta velit, eget elementum eros elit sed nisl. Sed consectetur vitae risus condimentum sollicitudin. Aliquam ultricies rhoncus justo sed semper. Sed in lorem porttitor, tincidunt ipsum sit amet, accumsan ipsum. Nam ac mattis nunc. Vivamus efficitur, tortor ac pulvinar auctor, enim nunc cursus odio, id tristique risus turpis eu nisi. Nunc luctus, mauris non tincidunt maximus, erat mi ultricies ligula, nec pretium nibh tellus a diam. Vestibulum blandit, neque sit amet varius convallis, diam ante venenatis ipsum, vitae gravida enim leo ac diam. Sed sapien sapien, pellentesque eu massa et, tempus placerat urna. Curabitur egestas, purus ac lobortis dignissim, ligula orci venenatis eros, non fermentum lectus tellus non leo. Etiam in accumsan quam. Nam vel ligula leo. Aenean in congue dolor. Sed dictum blandit velit vitae ultrices. Suspendisse nec ex vitae magna vehicula facilisis vel id justo. Nullam a semper erat. Sed venenatis ligula vel dignissim auctor. Quisque felis libero, aliquet vel diam a, rhoncus finibus ex."
                )
            }
            else -> {
                DomainEventDescriptionData(
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
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eget lobortis enim, ut convallis est. Curabitur sit amet ligula a orci elementum vulputate. Proin arcu lectus, volutpat eget aliquet nec, condimentum quis justo. Pellentesque tempor mauris quis tellus suscipit finibus. Aliquam at lobortis sapien, non iaculis mauris. In malesuada facilisis leo id cursus. Cras id risus fringilla, lacinia eros at, volutpat arcu. Etiam elementum sagittis pharetra. Praesent pretium elementum quam vel semper. In mollis, neque et efficitur blandit, magna nisl porta velit, eget elementum eros elit sed nisl. Sed consectetur vitae risus condimentum sollicitudin. Aliquam ultricies rhoncus justo sed semper. Sed in lorem porttitor, tincidunt ipsum sit amet, accumsan ipsum. Nam ac mattis nunc. Vivamus efficitur, tortor ac pulvinar auctor, enim nunc cursus odio, id tristique risus turpis eu nisi. Nunc luctus, mauris non tincidunt maximus, erat mi ultricies ligula, nec pretium nibh tellus a diam. Vestibulum blandit, neque sit amet varius convallis, diam ante venenatis ipsum, vitae gravida enim leo ac diam. Sed sapien sapien, pellentesque eu massa et, tempus placerat urna. Curabitur egestas, purus ac lobortis dignissim, ligula orci venenatis eros, non fermentum lectus tellus non leo. Etiam in accumsan quam. Nam vel ligula leo. Aenean in congue dolor. Sed dictum blandit velit vitae ultrices. Suspendisse nec ex vitae magna vehicula facilisis vel id justo. Nullam a semper erat. Sed venenatis ligula vel dignissim auctor. Quisque felis libero, aliquet vel diam a, rhoncus finibus ex."
                )
            }
        }
    }

    override fun getCommunicationPagerDataById(eventId: Long): DomainCommunicationPagerData {
        return when (eventId) {
            1L -> {
                DomainCommunicationPagerData(1L, "Kristóf's birtday party", true, 2)
            }
            2L -> {
                DomainCommunicationPagerData(2L, "Budapest sightseeing tour", false, 0)
            }
            else -> {
                DomainCommunicationPagerData(3L, "Going to gym", false, 0)
            }
        }
    }

    override fun getEventNewsById(eventId: Long): List<DomainEventNews> {
        return listOf(
            when (eventId) {
                1L -> {
                    DomainEventNews(
                        1L,
                        "We need more food!",
                        "KR1ST0F",
                        "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken."
                    )
                }
                2L -> {
                    DomainEventNews(
                        2L,
                        "Most likely it will rain!",
                        "Yanet Garcia",
                        "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken."
                    )
                }
                else -> {
                    DomainEventNews(
                        3L,
                        "Bull Shark Testosterone",
                        "Brucie Kibbutz",
                        "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken."
                    )
                }
            }
        )
    }

    override fun getEventQuestionsAndAnswersByEventId(eventId: Long): List<DomainEventQuestionAndAnswer> {
        return listOf(
            DomainEventQuestionAndAnswer(
                DomainEventQuestion(
                    1L,
                    "Has anyone seen my son?",
                    "Anakin Skywalker",
                    "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken.",
                    eventId
                ),
                DomainEventAnswer(
                    1L,
                    "I believe he is on Hoth.",
                    "Han Solo",
                    "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken.",
                    eventId
                ), eventId
            )
        )
    }

    override fun getEventQuestionsAndTitle(eventId: Long): DomainEventQuestionsWithTitle {
        return DomainEventQuestionsWithTitle(
            1L, "Kristóf's birthday party", listOf(
                DomainEventQuestion(
                    id = 8L,
                    eventId = 1L,
                    question = "Should I bring food?",
                    author = "B4RN1",
                    detailedQuestion = "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken."
                )
            )
        )
    }

    override fun sendInvitations(eventId: Long, userNames: List<String>): Boolean {
        return true
    }

    override fun getEventShortInfoByEventId(eventId: Long): DomainEventShortInfo {
        return DomainEventShortInfo(
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
        )
    }

    override fun modifyEvent(eventId: Long, eventDetails: DomainEventDetails): Boolean {
        return true
    }

    override fun uploadEvent(event: DomainAddableEvent): DomainUploadResponse {
        return DomainUploadResponse(true,"")
    }
}