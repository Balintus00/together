package hu.bme.aut.android.together.features.eventcontrol.communication.pager.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.features.eventcontrol.communication.pager.interactor.EventCommunicationPagerInteractor
import hu.bme.aut.android.together.model.domain.DomainCommunicationPagerData
import hu.bme.aut.android.together.model.presentation.CommunicationPagerData
import javax.inject.Inject

class EventCommunicationPagerPresenter @Inject constructor(
    private val interactor: EventCommunicationPagerInteractor
) {

    suspend fun loadPagerDataByEventId(eventId: Long) = withIOContext {
        interactor.loadPagerDataByEventId(eventId).toCommunicationPagerData()
    }

    private fun DomainCommunicationPagerData.toCommunicationPagerData(): CommunicationPagerData {
        return CommunicationPagerData(
            eventTitle, isOrganiser, organiserQuestionCount
        )
    }

}