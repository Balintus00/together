package hu.bme.aut.android.together.domain.interactor

import hu.bme.aut.android.together.domain.model.DomainProfileData
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.ProfileRepository
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val profileRepository: ProfileRepository
) {

    fun getProfileDataById(id: Long) : DomainProfileData {
        return networkDataSource.getUserProfileById(id)?.let { profileData ->
            profileRepository.saveProfileData(profileData)
            profileData
        } ?: profileRepository.loadProfileData(id)
    }

}