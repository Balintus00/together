package hu.bme.aut.android.together.persistence.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.together.model.persistence.PersistedEventDescriptionData
import hu.bme.aut.android.together.model.persistence.PersistedEventDetails

@Dao
interface EventDetailsDao {

    @Query("SELECT * FROM persistedeventdetails WHERE id=:eventId")
    fun getCachedEventDetailsById(eventId: Long) : PersistedEventDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCachedEventDetails(details: PersistedEventDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = PersistedEventDetails::class)
    fun insertCachedEventDescriptionDetails(data: PersistedEventDescriptionData)

}