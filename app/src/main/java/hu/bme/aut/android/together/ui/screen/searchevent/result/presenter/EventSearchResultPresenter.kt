package hu.bme.aut.android.together.ui.screen.searchevent.result.presenter

import java.text.SimpleDateFormat
import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.domain.interactor.EventSearchResultInteractor
import hu.bme.aut.android.together.domain.model.DomainEventQueryParameter
import hu.bme.aut.android.together.domain.model.DomainEventShortInfo
import hu.bme.aut.android.together.ui.model.EventQueryParameter
import hu.bme.aut.android.together.ui.model.EventShortInfo
import java.text.ParseException
import java.util.*
import javax.inject.Inject

class EventSearchResultPresenter @Inject constructor(
    val interactor: EventSearchResultInteractor
) {

    suspend fun loadSearchResults(searchParameters: EventQueryParameter) = withIOContext {
        searchParameters.toDomainEventQueryParameter()
        interactor.loadResultsByQueryParameters(searchParameters.toDomainEventQueryParameter())
            .map {
                it.toEventShortInfo()
            }
    }

    private fun EventQueryParameter.toDomainEventQueryParameter(): DomainEventQueryParameter {
        return DomainEventQueryParameter(
            name,
            place,
            radius,
            parseDateStringOnErrorPassNull(startDate, startTime),
            parseDateStringOnErrorPassNull(endDate, endTime),
            type
        )
    }

    private fun DomainEventShortInfo.toEventShortInfo(): EventShortInfo {
        return EventShortInfo(
            eventId,
            name,
            location,
            convertDateToRepresentedDateFormat(startDate),
            convertDateToRepresentedDateFormat(endDate),
            imageUrl
        )
    }

    private fun convertDateToRepresentedDateFormat(date: Date): String {
        return SimpleDateFormat("EEEE, MMM dd - HH:mm", Locale.ENGLISH).format(date)
    }

    private fun parseDateStringOnErrorPassNull(dateString: String, timeString: String): Date? {
        return try {
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("$dateString $timeString") }!!
        } catch (pe: ParseException) {
            null
        }
    }

}