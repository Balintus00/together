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
        return when (eventId) {
            1L -> {
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
            2L -> {
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
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
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
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
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
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return listOf(
            when (eventId) {
                1L -> {
                    DomainEventNews(1L, "We need more food!", "KR1ST0F", "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken.")
                }
                2L -> {
                    DomainEventNews(2L, "Most likely it will rain!", "Yanet Garcia", "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken.")
                }
                else -> {
                    DomainEventNews(3L, "Bull Shark Testosterone", "Brucie Kibbutz", "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken.")
                }
            }
        )
    }

    override fun getEventQuestionsAndAnswersByEventId(eventId: Long): List<DomainEventQuestionAndAnswer> {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return listOf(
            DomainEventQuestionAndAnswer(
                DomainEventQuestion(
                    1L, "Has anyone seen my son?", "Anakin Skywalker", "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken.",
                    eventId
                ),
                DomainEventAnswer(
                    1L, "I believe he is on Hoth.", "Han Solo", "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken.",
                    eventId
                ), eventId
            )
        )
    }

    override fun getEventQuestionsAndTitle(eventId: Long): DomainEventQuestionsWithTitle {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return DomainEventQuestionsWithTitle(1L, "Kristóf's birthday party", listOf(
            DomainEventQuestion(id = 8L, eventId = 1L,  question = "Should I bring food?", author = "B4RN1", detailedQuestion =  "Spicy jalapeno bacon ipsum dolor amet kielbasa fatback corned beef meatloaf turducken burgdoggen pork belly brisket jowl biltong swine ribeye. Chuck pork pork loin pastrami kielbasa. Porchetta buffalo filet mignon corned beef swine frankfurter. Bacon short loin chuck landjaeger andouille. Corned beef beef ribs picanha leberkas short ribs. Chuck beef tail meatball buffalo filet mignon rump sirloin prosciutto ground round pork belly shoulder tri-tip chicken.")
        ))
    }

    override fun sendInvitations(eventId: Long, userNames: List<String>): Boolean {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return true
    }

    override fun getEventShortInfoByEventId(eventId: Long): DomainEventShortInfo {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return DomainEventShortInfo(
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
        )
    }

    override fun modifyEvent(eventId: Long, eventDetails: DomainEventDetails): Boolean {
        return true
    }

    override fun uploadEvent(event: DomainAddableEvent): DomainUploadResponse {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return DomainUploadResponse(true, "")
    }

}