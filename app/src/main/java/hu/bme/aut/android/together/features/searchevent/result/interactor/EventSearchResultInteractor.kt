package hu.bme.aut.android.together.features.searchevent.result.interactor

import android.util.Log
import hu.bme.aut.android.together.model.domain.DomainEventQueryParameter
import hu.bme.aut.android.together.model.domain.DomainEventShortInfo
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventShortInfoRepository
import javax.inject.Inject

class EventSearchResultInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val eventShortInfoRepository: EventShortInfoRepository
) {

    fun loadResultsByQueryParameters(parameters: DomainEventQueryParameter) : List<DomainEventShortInfo> {
        Log.d("Together!", "Hello")
        return networkDataSource.searchEventsByQueryParameter(parameters)?.let { results ->
            eventShortInfoRepository.persistResultEventShortInfo(*results.toTypedArray())
            results
        } ?: eventShortInfoRepository.loadCachedResultEventShortInfo()
    }

}