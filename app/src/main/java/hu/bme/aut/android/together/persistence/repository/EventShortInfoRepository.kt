package hu.bme.aut.android.together.persistence.repository

import android.database.sqlite.SQLiteException
import android.util.Log
import hu.bme.aut.android.together.model.domain.DomainEventShortInfo
import hu.bme.aut.android.together.model.persistence.PersistedEventShortInfo
import hu.bme.aut.android.together.model.persistence.PersistedEventShortInfoType
import hu.bme.aut.android.together.persistence.dao.EventShortInfoDao
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventShortInfoRepository @Inject constructor(
    private val eventShortInfoDao: EventShortInfoDao
) {

    companion object {
        const val LOADING_EXCEPTION_MESSAGE = "LOADING_EXCEPTION"
        const val SAVING_EXCEPTION_MESSAGE = "SAVING_EXCEPTION"
    }

    /**
     * @throws RuntimeException if the loading was unsuccessful.
     */
    fun loadCachedComingEventShortInfo(): List<DomainEventShortInfo> {
        try {
            return eventShortInfoDao.getShortInfoByPersistenceOption(PersistedEventShortInfoType.ComingEvent.ordinal)
                .map {
                    it.toDomainEventShortInfo()
                }
        } catch (e: SQLiteException) {
            throw RuntimeException(LOADING_EXCEPTION_MESSAGE)
        }
    }

    /**
     * @throws RuntimeException if the loading was unsuccessful.
     */
    fun loadCachedPastEventShortInfo(): List<DomainEventShortInfo> {
        try {
            return eventShortInfoDao.getShortInfoByPersistenceOption(PersistedEventShortInfoType.PastEvent.ordinal)
                .map {
                    it.toDomainEventShortInfo()
                }
        } catch (e: SQLiteException) {
            Log.e("Together!", e.stackTraceToString())
            throw RuntimeException(LOADING_EXCEPTION_MESSAGE)
        }
    }

    /**
     * @throws RuntimeException if the loading was unsuccessful.
     */
    fun loadCachedResultEventShortInfo(): List<DomainEventShortInfo> {
        return eventShortInfoDao.getShortInfoByPersistenceOption(PersistedEventShortInfoType.ResultEvent.ordinal)
            .map {
                it.toDomainEventShortInfo()
            }
    }

    /**
     * @throws RuntimeException if the saving was unsuccessful.
     */
    fun persistResultEventShortInfo(vararg eventShortInfo: DomainEventShortInfo) {
        try {
            eventShortInfoDao.insertCachedEventShortInfo(*eventShortInfo.map {
                it.toPersistedEventShortInfo(PersistedEventShortInfoType.ResultEvent.ordinal)
            }.toTypedArray())
        } catch (e: SQLiteException) {
            Log.e("Together!", e.stackTraceToString())
            throw RuntimeException(SAVING_EXCEPTION_MESSAGE)
        }
    }

    /**
     * @throws RuntimeException if the saving was unsuccessful.
     */
    fun persistComingEventShortInfo(vararg eventShortInfo: DomainEventShortInfo) {
        try {
            eventShortInfoDao.insertCachedEventShortInfo(*eventShortInfo.map {
                it.toPersistedEventShortInfo(PersistedEventShortInfoType.ComingEvent.ordinal)
            }.toTypedArray())
        } catch (e: SQLiteException) {
            Log.e("Together!", e.stackTraceToString())
            throw RuntimeException(SAVING_EXCEPTION_MESSAGE)
        }
    }

    /**
     * @throws RuntimeException if the saving was unsuccessful.
     */
    fun persistPastEventShortInfo(vararg eventShortInfo: DomainEventShortInfo) {
        try {
            eventShortInfoDao.insertCachedEventShortInfo(*eventShortInfo.map {
                it.toPersistedEventShortInfo(PersistedEventShortInfoType.PastEvent.ordinal)
            }.toTypedArray())
        } catch (e: SQLiteException) {
            Log.e("Together!", e.stackTraceToString())
            throw RuntimeException(SAVING_EXCEPTION_MESSAGE)
        }
    }

    private fun DomainEventShortInfo.toPersistedEventShortInfo(cachingType: Int): PersistedEventShortInfo {
        require(cachingType in PersistedEventShortInfoType.values().map { it.ordinal })
        val startCalendar = Calendar.getInstance().apply { time = startDate }
        val endCalendar = Calendar.getInstance().apply { time = endDate }
        return PersistedEventShortInfo(
            eventId,
            name,
            location,
            startCalendar.run { "${get(Calendar.YEAR)}.${get(Calendar.MONTH) + 1}.${get(Calendar.DAY_OF_MONTH)}." },
            startCalendar.run { "${get(Calendar.HOUR_OF_DAY)}:${get(Calendar.MINUTE)}" },
            endCalendar.run { "${get(Calendar.YEAR)}.${get(Calendar.MONTH) + 1}.${get(Calendar.DAY_OF_MONTH)}." },
            endCalendar.run { "${get(Calendar.HOUR_OF_DAY)}:${get(Calendar.MINUTE)}" },
            imageUrl,
            cachingType
        )
    }

    private fun PersistedEventShortInfo.toDomainEventShortInfo(): DomainEventShortInfo {
        return DomainEventShortInfo(
            eventId,
            name,
            location,
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("$startDate $startTime") }!!,
            SimpleDateFormat(
                "yyyy.MM.dd. HH:mm",
                Locale.ENGLISH
            ).run { parse("$endDate $endTime") }!!,
            imageUrl
        )
    }

}