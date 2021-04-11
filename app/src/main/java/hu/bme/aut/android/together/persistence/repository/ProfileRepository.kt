package hu.bme.aut.android.together.persistence.repository

import hu.bme.aut.android.together.model.domain.DomainProfileData
import hu.bme.aut.android.together.model.persistence.PersistedProfileData
import hu.bme.aut.android.together.persistence.dao.ProfileDao
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileDao: ProfileDao
) {

    fun saveProfileData(domainProfileData: DomainProfileData) {
        profileDao.insertProfileData(domainProfileData.toPersistedProfileData())
    }

    fun loadProfileData(profileId: Long): DomainProfileData {
        return profileDao.getProfileDataById(profileId).toDomainProfileData()
    }

    private fun DomainProfileData.toPersistedProfileData(): PersistedProfileData {
        return this.let {
            PersistedProfileData(
                it.profileId,
                it.name,
                it.username,
                Calendar.getInstance().run {
                    time = it.dateOfBirth
                    "${get(Calendar.YEAR)}.${get(Calendar.MONTH) + 1}.${get(Calendar.DAY_OF_MONTH)}"
                },
                it.profileImageUri,
                it.incomingInvitesCount
            )
        }
    }

    private fun PersistedProfileData.toDomainProfileData(): DomainProfileData {
        return this.let {
            DomainProfileData(
                it.id,
                it.name,
                it.username,
                SimpleDateFormat("yyyy.MM.d.", Locale.ENGLISH).run { parse(it.dateOfBirth) }!!,
                it.profileImageUri,
                it.incomingInvitesCount
            )
        }
    }
}