package hu.bme.aut.android.together.features.addevent.pagerelement.overview.interactor

import hu.bme.aut.android.together.model.domain.DomainAddableEvent
import hu.bme.aut.android.together.model.domain.DomainUploadResponse
import hu.bme.aut.android.together.network.NetworkDataSource
import javax.inject.Inject

class OverviewInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource
) {

    fun uploadEvent(event: DomainAddableEvent): DomainUploadResponse {
        return networkDataSource.uploadEvent(event)
    }

}