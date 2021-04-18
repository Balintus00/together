package hu.bme.aut.android.together.features.currentevents.presenter

import android.content.Context
import co.zsmb.rainbowcake.withIOContext
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.features.currentevents.interactor.EventListInteractor
import hu.bme.aut.android.together.model.presentation.EventShortInfo
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PastEventListPresenter @Inject constructor(
    @ApplicationContext private val context: Context,
    private val eventListInteractor: EventListInteractor
) {

    /**
     * @throws RuntimeException if data couldn't be loaded. Also sets the exception's message
     * to the message, that should represented by the view layer.
     */
    suspend fun loadPastEventShortInfoByProfileId(profileId: Long) = withIOContext {
        try {
            eventListInteractor.getPastEventShortInfoByProfileId(profileId)
                .map { domainShortInfoModel ->
                    EventShortInfo(
                        domainShortInfoModel.eventId,
                        domainShortInfoModel.name,
                        domainShortInfoModel.location,
                        convertDateToRepresentedDateFormat(domainShortInfoModel.startDate),
                        convertDateToRepresentedDateFormat(domainShortInfoModel.endDate),
                        domainShortInfoModel.imageUrl
                    )
                }
        } catch (e: RuntimeException) {
            throw RuntimeException(context.getString(R.string.error_message_loading))
        }
    }

    private fun convertDateToRepresentedDateFormat(date: Date): String {
        return SimpleDateFormat("EEEE, MMM dd - HH:mm", Locale.ENGLISH).format(date)
    }

}