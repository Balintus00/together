package hu.bme.aut.android.together.model.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersistedProfileData(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: String,
    @ColumnInfo(name = "profile_image_uri") val profileImageUri: String,
    @ColumnInfo(name = "incoming_invites_count") val incomingInvitesCount: Int
)