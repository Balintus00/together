package hu.bme.aut.android.together.persistence.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.together.model.persistence.PersistedEventShortInfo

@Dao
interface EventShortInfoDao {

    @Query("SELECT * FROM persistedeventshortinfo WHERE isComing = 1")
    @Throws(SQLiteException::class)
    fun getCachedComingEventShortInfo() : List<PersistedEventShortInfo>

    @Query("SELECT * FROM persistedeventshortinfo WHERE isComing = 0")
    @Throws(SQLiteException::class)
    fun getPastComingEventShortInfo() : List<PersistedEventShortInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    fun insertCachedEventShortInfo(vararg eventShortInfo : PersistedEventShortInfo)

}