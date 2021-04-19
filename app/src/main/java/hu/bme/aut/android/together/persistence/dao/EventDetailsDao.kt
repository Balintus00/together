package hu.bme.aut.android.together.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.together.model.persistence.PersistedEventDetails

@Dao
interface EventDetailsDao {

    @Query("SELECT * FROM persistedeventdetails WHERE id=:eventId")
    fun getCachedEventDetailsById(eventId: Long) : PersistedEventDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun cacheEventDetails(details: PersistedEventDetails)

}