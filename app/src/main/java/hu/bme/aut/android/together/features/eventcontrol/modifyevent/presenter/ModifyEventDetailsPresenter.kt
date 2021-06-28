package hu.bme.aut.android.together.features.eventcontrol.modifyevent.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.features.eventcontrol.modifyevent.interactor.ModifyEventDetailsInteractor
import hu.bme.aut.android.together.domain.model.DomainEventDetails
import hu.bme.aut.android.together.model.presentation.EventDetails
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ModifyEventDetailsPresenter @Inject constructor(
    private val interactor: ModifyEventDetailsInteractor
) {

    suspend fun loadCurrentEventDetails(eventId: Long) = withIOContext {
        interactor.getCurrentEventDetails(eventId).toEventDetails()
    }

    suspend fun sendEventModificationRequest(eventId: Long, eventDetails: EventDetails) =
        withIOContext {
            interactor.uploadModifiedEventDetails(eventId, eventDetails.toDomainEventDetails())
        }

    private fun EventDetails.toDomainEventDetails(): DomainEventDetails {
        return DomainEventDetails(
            eventId,
            title,
            imageUrl,
            category,
            SimpleDateFormat("yyyy.MM.DD. HH:mm", Locale.ENGLISH).run { parse(startDate) }!!,
            SimpleDateFormat("yyyy.MM.DD. HH:mm", Locale.ENGLISH).run { parse(endDate) }!!,
            location,
            description,
            isParticipantCountLimited,
            maxParticipantCount,
            currentParticipantCount,
            isPrivate,
            isParticipant,
            isOrganiser
        )
    }

    private fun DomainEventDetails.toEventDetails(): EventDetails {
        return EventDetails(
            id,
            title,
            imageUrl,
            category,
            convertDateToRepresentedDateFormat(startDate),
            convertDateToRepresentedDateFormat(endDate),
            location,
            description,
            isParticipantCountLimited,
            maxParticipantCount,
            currentParticipantCount,
            isPrivate,
            isParticipant,
            isOrganiser
        )
    }

    private fun convertDateToRepresentedDateFormat(date: Date): String {
        return SimpleDateFormat("yyyy.MM.dd. HH:mm", Locale.ENGLISH).format(date)
    }

}