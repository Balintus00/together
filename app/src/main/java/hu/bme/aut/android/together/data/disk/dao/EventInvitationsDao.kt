package hu.bme.aut.android.together.data.disk.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.together.data.disk.model.PersistedEventInvitation

@Dao
interface EventInvitationsDao {

    @Query("SELECT * FROM persistedeventinvitation")
    fun getCachedIncomingEventInvitations() : List<PersistedEventInvitation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncomingEventInvitations(vararg invitations: PersistedEventInvitation)

}