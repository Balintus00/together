package hu.bme.aut.android.together.domain.interactor

import hu.bme.aut.android.together.domain.model.DomainAddableEvent
import hu.bme.aut.android.together.domain.model.DomainUploadResponse
import hu.bme.aut.android.together.data.network.NetworkDataSource
import javax.inject.Inject

class OverviewInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource
) {

    fun uploadEvent(event: DomainAddableEvent): DomainUploadResponse {
        return networkDataSource.uploadEvent(event)
    }

}