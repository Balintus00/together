package hu.bme.aut.android.together.domain.interactor

import hu.bme.aut.android.together.data.disk.datasource.UserProfileDatasource
import hu.bme.aut.android.together.domain.model.DomainUserProfile
import hu.bme.aut.android.together.data.network.NetworkDataSource
import java.io.IOException
import javax.inject.Inject

/**
 * Interactor to access user profile and logic related to it.
 */
class UserProfileInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val userProfileDatasource: UserProfileDatasource
) {

    /**
     * Attempts to load the user profile from the application's cache storage.
     * @param userId the user profile's ID.
     * @return the searched user profile.
     * @throws IOException if no user profile was found in the cache with the given ID.
     */
    suspend fun loadUserProfileById(userId: Long): DomainUserProfile {
        return userProfileDatasource.getUserProfileById(userId)
            ?: throw IOException()
    }

    /**
     * Loads the user profile from the backend, and persists it into the application cache storage.
     * @param userId the user profile's ID.
     * @throws IOException if the data refreshing was unsuccessful.
     */
    suspend fun refreshUserProfileById(userId: Long) {
        networkDataSource.getUserProfileById(userId)?.also {
            userProfileDatasource.persistUserProfile(it)
        } ?: throw IOException()
    }

}