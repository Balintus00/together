package hu.bme.aut.android.together.persistence

import android.content.Context
import androidx.room.Room
import hu.bme.aut.android.together.model.domain.DomainProfileData
import hu.bme.aut.android.together.model.persistence.PersistedProfileData
import hu.bme.aut.android.together.persistence.database.AppDatabase
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PersistenceManager @Inject constructor(
    context: Context
) {

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()

    private val profileDao = db.profileDao()

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