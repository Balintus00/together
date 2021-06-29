package hu.bme.aut.android.together.data.disk.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.together.data.disk.model.PersistedCommunicationPagerData
import hu.bme.aut.android.together.data.disk.model.PersistedEventDescriptionData
import hu.bme.aut.android.together.data.disk.model.PersistedEventData
import hu.bme.aut.android.together.data.disk.model.PersistedEventDetails

@Dao
interface EventDataDao {

    @Query("SELECT * FROM persistedeventdata WHERE id=:eventId")
    fun getCachedEventDetailsById(eventId: Long) : PersistedEventData

    @Query("SELECT title FROM PersistedEventData WHERE id=:eventId")
    fun getEventTitleById(eventId: Long): String

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = PersistedEventData::class)
    fun insertCachedEventDetails(data: PersistedEventDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = PersistedEventData::class)
    fun insertCachedEventDescriptionDetails(data: PersistedEventDescriptionData)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = PersistedEventData::class)
    fun insertCachedEventCommunicationPagerData(data: PersistedCommunicationPagerData)

}