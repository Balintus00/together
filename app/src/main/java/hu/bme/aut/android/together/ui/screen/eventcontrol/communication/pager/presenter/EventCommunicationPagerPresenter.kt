package hu.bme.aut.android.together.ui.screen.eventcontrol.communication.pager.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.domain.interactor.EventCommunicationPagerInteractor
import hu.bme.aut.android.together.domain.model.DomainCommunicationPagerData
import hu.bme.aut.android.together.ui.model.CommunicationPagerData
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