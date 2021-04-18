package hu.bme.aut.android.together.features.searchevent.result.presenter

import android.util.Log
import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.features.searchevent.result.interactor.EventSearchResultInteractor
import hu.bme.aut.android.together.model.domain.DomainEventQueryParameter
import hu.bme.aut.android.together.model.domain.DomainEventShortInfo
import hu.bme.aut.android.together.model.presentation.EventQueryParameter
import hu.bme.aut.android.together.model.presentation.EventShortInfo
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventSearchResultPresenter @Inject constructor(
    val interactor: EventSearchResultInteractor
) {

    suspend fun loadSearchResults(searchParameters: EventQueryParameter) = withIOContext {
        Log.d("Together!", "Hello")
        searchParameters.toDomainEventQueryParameter()
        Log.d("Together!", "Hello")
        interactor.loadResultsByQueryParameters(searchParameters.toDomainEventQueryParameter()).map {
            Log.d("Together!", "Hello")
            it.toEventShortInfo()
        }
    }

    private fun EventQueryParameter.toDomainEventQueryParameter() : DomainEventQueryParameter {
        return DomainEventQueryParameter(
            name,
            place,
            radius,
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("${this@toDomainEventQueryParameter.fromDate} ${this@toDomainEventQueryParameter.fromTime}") }!!,
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("${this@toDomainEventQueryParameter.toDate} ${this@toDomainEventQueryParameter.toTime}") }!!,
            type
        )
    }

    private fun DomainEventShortInfo.toEventShortInfo() : EventShortInfo {
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
        return SimpleDateFormat("yyyy, MMM dd - HH:mm", Locale.ENGLISH).format(date)
    }

}