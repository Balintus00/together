package hu.bme.aut.android.together.data.disk.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PersistedUserProfile(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: Date,
    @ColumnInfo(name = "profile_image_uri") val profileImageUri: String,
    @ColumnInfo(name = "incoming_invites_count") val incomingInvitesCount: Int
)