package hu.bme.aut.android.together.domain.interactor

import android.util.Log
import hu.bme.aut.android.together.domain.model.DomainEventShortInfo
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventShortInfoRepository
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
                    eventShortInfoRepository.persistComingEventShortInfo(*infoList.toTypedArray())
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
                eventShortInfoRepository.persistPastEventShortInfo(*infoList.toTypedArray())
            } catch (e: RuntimeException) {
                Log.e("Together!", "Persisting data into database failed. Stacktrace:")
                Log.e("Together!", e.stackTraceToString())
            }
            infoList
        } ?: eventShortInfoRepository.loadCachedPastEventShortInfo()
    }

}