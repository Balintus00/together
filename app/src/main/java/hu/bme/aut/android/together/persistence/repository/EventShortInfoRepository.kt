package hu.bme.aut.android.together.persistence.repository

import android.util.Log
import hu.bme.aut.android.together.model.domain.DomainEventShortInfo
import hu.bme.aut.android.together.model.persistence.PersistedEventShortInfo
import hu.bme.aut.android.together.persistence.dao.EventShortInfoDao
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventShortInfoRepository @Inject constructor(
    private val eventShortInfoDao: EventShortInfoDao
) {

    fun loadCachedComingEventShortInfo(): List<DomainEventShortInfo> {
        return eventShortInfoDao.getCachedComingEventShortInfo().map {
            it.toDomainEventShortInfo()
        }
    }

    fun loadCachedPastEventShortInfo(): List<DomainEventShortInfo> {
        return eventShortInfoDao.getPastComingEventShortInfo().map {
            it.toDomainEventShortInfo()
        }
    }

    fun persistEventShortInfo(vararg eventShortInfo: DomainEventShortInfo) {
        try {
            eventShortInfoDao.insertCachedEventShortInfo(*eventShortInfo.map {
                it.toPersistedEventShortInfo()
            }.toTypedArray())
        } catch (e: Exception) {
            Log.e("Together!", e.stackTraceToString())
        }
    }

    private fun DomainEventShortInfo.toPersistedEventShortInfo(): PersistedEventShortInfo {
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
            isComing
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
            imageUrl,
            isComing
        )
    }

}