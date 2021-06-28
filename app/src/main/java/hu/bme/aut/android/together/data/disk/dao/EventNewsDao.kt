package hu.bme.aut.android.together.data.disk.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.together.data.disk.model.PersistedEventNews

@Dao
interface EventNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun persistEventNews(vararg new: PersistedEventNews)

    @Query("SELECT * FROM persistedeventnews WHERE eventId=:eventId")
    fun getCachedNewsByEventId(eventId: Long): List<PersistedEventNews>

}