package hu.bme.aut.android.together.data.disk.repository

import hu.bme.aut.android.together.domain.model.DomainEventShortInfo
import hu.bme.aut.android.together.data.disk.model.PersistedEventShortInfo
import hu.bme.aut.android.together.data.disk.model.PersistedEventShortInfoType
import hu.bme.aut.android.together.persistence.dao.EventShortInfoDao
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class InvitationSenderRepository @Inject constructor(
    private val eventShortInfoDao: EventShortInfoDao
) {

    fun loadEventShortInfo(eventId: Long): DomainEventShortInfo {
        return eventShortInfoDao.getEventShortInfoByEventId(eventId).toDomainEventShortInfo()
    }

    fun persistEventShortInfo(shortInfo: DomainEventShortInfo) {
        eventShortInfoDao.insertCachedEventShortInfo(
            shortInfo.toPersistedEventShortInfo(
                PersistedEventShortInfoType.ComingEvent.ordinal
            )
        )
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