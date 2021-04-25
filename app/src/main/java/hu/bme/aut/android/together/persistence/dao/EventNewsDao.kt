package hu.bme.aut.android.together.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.together.model.persistence.PersistedEventNews

@Dao
interface EventNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun persistEventNews(vararg new: PersistedEventNews)

    @Query("SELECT * FROM persistedeventnews WHERE eventId=:eventId")
    fun getCachedNewsByEventId(eventId: Long): List<PersistedEventNews>

}