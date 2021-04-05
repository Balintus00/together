package hu.bme.aut.android.together.features.profile.interactor

import hu.bme.aut.android.together.model.domain.DomainProfileData
import hu.bme.aut.android.together.network.NetworkManager
import hu.bme.aut.android.together.persistence.PersistenceManager
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val networkManager: NetworkManager,
    //private val persistenceManager: PersistenceManager
) {

    fun getProfileDataById(id: Long) : DomainProfileData {
        return networkManager.getUserProfileById(id)?.let { profileData ->
            //persistenceManager.saveProfileData(profileData)
            profileData
        }!! //?: persistenceManager.loadProfileData(id)
    }

}