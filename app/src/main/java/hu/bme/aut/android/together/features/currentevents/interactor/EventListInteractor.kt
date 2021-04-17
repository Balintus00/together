package hu.bme.aut.android.together.features.currentevents.interactor

import android.util.Log
import hu.bme.aut.android.together.model.domain.DomainEventShortInfo
import hu.bme.aut.android.together.network.NetworkDataSource
import hu.bme.aut.android.together.persistence.repository.EventShortInfoRepository
import javax.inject.Inject

class EventListInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val eventShortInfoRepository: EventShortInfoRepository
) {

    fun getComingEventShortInfoByProfileId(profileId: Long): List<DomainEventShortInfo> {
        return networkDataSource.getComingEventShortInfoListByProfileId(profileId)
            ?.let { infoList ->
                eventShortInfoRepository.persistEventShortInfo(*infoList.toTypedArray())
                infoList
            } ?: eventShortInfoRepository.loadCachedComingEventShortInfo()
    }

    fun getPastEventShortInfoByProfileId(profileId: Long): List<DomainEventShortInfo> {
        return networkDataSource.getPastEventShortInfoListByProfileId(profileId)?.let { infoList ->
            Log.d("Together!", "Before call")
            eventShortInfoRepository.persistEventShortInfo(*infoList.toTypedArray())
            Log.d("Together!", "After call")
            infoList
        } ?: eventShortInfoRepository.loadCachedPastEventShortInfo()
    }

}