package hu.bme.aut.android.together.data.disk.datasource

import hu.bme.aut.android.together.data.disk.dao.UserProfileDao
import hu.bme.aut.android.together.data.disk.model.PersistedUserProfile
import hu.bme.aut.android.together.domain.model.DomainUserProfile
import javax.inject.Inject

/**
 * Persistent datasource for user profiles.
 */
class UserProfileDatasource @Inject constructor(
    private val userProfileDao: UserProfileDao
) {

    /**
     * Loads from the application's cache storage the user profile with the given ID.
     * @param profileId the searched user profile's ID.
     * @return the searched user profile. If no profile was found with the given ID, the function
     * returns null.
     */
    suspend fun getUserProfileById(profileId: Long): DomainUserProfile? {
        return userProfileDao.getUserProfileById(profileId)?.toDomainUserProfile()
    }

    /**
     * Caches the given user profiles.
     * This operation always should succeed.
     * @param profiles the user profiles, that will be cached.
     */
    suspend fun persistUserProfile(vararg profiles: DomainUserProfile) {
        userProfileDao.insertUserProfile(*profiles.map { it.toPersistedUserProfile() }
            .toTypedArray())
    }

    private fun DomainUserProfile.toPersistedUserProfile(): PersistedUserProfile {
        return this.let {
            PersistedUserProfile(
                it.profileId,
                it.name,
                it.username,
                it.dateOfBirth,
                it.profileImageUri,
                it.incomingInvitesCount
            )
        }
    }

    private fun PersistedUserProfile.toDomainUserProfile(): DomainUserProfile {
        return this.let {
            DomainUserProfile(
                it.id,
                it.name,
                it.username,
                it.dateOfBirth,
                it.profileImageUri,
                it.incomingInvitesCount
            )
        }
    }
}