package hu.bme.aut.android.together.ui.screen.addevent.pagerelement.overview.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.domain.interactor.OverviewInteractor
import hu.bme.aut.android.together.domain.model.DomainAddableEvent
import hu.bme.aut.android.together.domain.model.DomainUploadResponse
import hu.bme.aut.android.together.ui.model.AddableEvent
import hu.bme.aut.android.together.ui.model.UploadResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class OverviewPresenter @Inject constructor(
    private val interactor: OverviewInteractor
) {

    suspend fun uploadEvent(event: AddableEvent) = withIOContext {
        interactor.uploadEvent(event.toDomainAddableEvent()).toUploadResponse()
    }

    private fun AddableEvent.toDomainAddableEvent() : DomainAddableEvent {
        return DomainAddableEvent(
            title,
            isPrivate,
            isMaximumParticipantCountRuleSet,
            maximumParticipantCount,
            isJoinRequestAutoAcceptAllowed,
            category,
            SimpleDateFormat("yyyy.MM.dd. HH:mm", Locale.ENGLISH).run { parse("$startDate $startTime") }!!,
            SimpleDateFormat("yyyy.MM.dd. HH:mm", Locale.ENGLISH).run { parse("$endDate $endTime") }!!,
            location,
            description
        )
    }

    private fun DomainUploadResponse.toUploadResponse(): UploadResponse {
        return UploadResponse(wasSuccessful, errorMessage)
    }

}