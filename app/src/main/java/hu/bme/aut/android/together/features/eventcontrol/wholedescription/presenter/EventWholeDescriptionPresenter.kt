package hu.bme.aut.android.together.features.eventcontrol.wholedescription.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.features.eventcontrol.wholedescription.interactor.EventWholeDescriptionInteractor
import hu.bme.aut.android.together.model.domain.DomainEventDescriptionData
import hu.bme.aut.android.together.model.presentation.EventDescriptionScreenData
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventWholeDescriptionPresenter @Inject constructor(
    private val interactor: EventWholeDescriptionInteractor
) {
    suspend fun getEventDescriptionScreenData(eventId: Long) = withIOContext {
        interactor.getEventDescriptionDataByEventId(eventId).toEventScreenData()
    }

    private fun DomainEventDescriptionData.toEventScreenData(): EventDescriptionScreenData {
        return EventDescriptionScreenData(
            title,
            convertDateToRepresentedDateFormat(startDateTime),
            convertDateToRepresentedDateFormat(endDateTime),
            location,
            description
        )
    }

    private fun convertDateToRepresentedDateFormat(date: Date): String {
        return SimpleDateFormat("EEEE, MMM dd - HH:mm", Locale.ENGLISH).format(date)
    }
}