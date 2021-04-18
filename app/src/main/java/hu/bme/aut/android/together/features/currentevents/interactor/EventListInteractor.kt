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

    /**
     * @throws RuntimeException if loading cached data is needed, and this operation fails.
     */
    fun getComingEventShortInfoByProfileId(profileId: Long): List<DomainEventShortInfo> {
        return networkDataSource.getComingEventShortInfoListByProfileId(profileId)
            ?.let { infoList ->
                try {
                    eventShortInfoRepository.persistEventShortInfo(*infoList.toTypedArray())
                } catch (e: RuntimeException) {
                    Log.e("Together!", "Persisting data into database failed. Stacktrace:")
                    Log.e("Together!", e.stackTraceToString())
                }
                infoList
            } ?: try {
            eventShortInfoRepository.loadCachedComingEventShortInfo()
        } catch (e: RuntimeException) {
            Log.e("Together!", "Loading cached data failed. Stacktrace:")
            Log.e("Together!", e.stackTraceToString())
            throw e
        }
    }

    /**
     * @throws RuntimeException if loading cached data is needed, and this operation fails.
     */
    fun getPastEventShortInfoByProfileId(profileId: Long): List<DomainEventShortInfo> {
        return networkDataSource.getPastEventShortInfoListByProfileId(profileId)?.let { infoList ->
            try {
                eventShortInfoRepository.persistEventShortInfo(*infoList.toTypedArray())
            } catch (e: RuntimeException) {
                Log.e("Together!", "Persisting data into database failed. Stacktrace:")
                Log.e("Together!", e.stackTraceToString())
            }
            infoList
        } ?: eventShortInfoRepository.loadCachedPastEventShortInfo()
    }

}