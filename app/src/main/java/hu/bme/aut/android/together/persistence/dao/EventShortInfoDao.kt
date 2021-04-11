package hu.bme.aut.android.together.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.together.model.persistence.PersistedEventShortInfo

@Dao
interface EventShortInfoDao {

    @Query("SELECT * FROM persistedeventshortinfo WHERE isComing = 1")
    fun getCachedComingEventShortInfo() : List<PersistedEventShortInfo>

    @Query("SELECT * FROM persistedeventshortinfo WHERE isComing = 0")
    fun getPastComingEventShortInfo() : List<PersistedEventShortInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCachedEventShortInfo(vararg eventShortInfo : PersistedEventShortInfo)

}