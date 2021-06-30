package hu.bme.aut.android.together.data.disk.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.together.data.disk.model.PersistedUserProfile

/**
 * Data access object for persisted user profiles.
 */
@Dao
interface UserProfileDao {

    /**
     * Loads the user profile with the given ID from the database.
     * @param id the searched persisted user profile's ID.
     * @return the persisted user profile, or null, if no profile was found with the given ID.
     */
    @Query("SELECT * FROM persisteduserprofile WHERE id = :id")
    suspend fun getUserProfileById(id: Long): PersistedUserProfile?

    /**
     * Persists the given user profiles into the database.
     * If the entity has been persisted before, then it's data will be simply overwritten.
     * @param userProfiles the user profiles, that will be persisted into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(vararg userProfiles: PersistedUserProfile)
}