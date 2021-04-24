package hu.bme.aut.android.together.model.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PersistedEventDetails(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "imageUrl", defaultValue = "") val imageUrl: String,
    @ColumnInfo(name = "startDate") val startDate: String,
    @ColumnInfo(name = "endDate") val endDate: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(
        name = "isParticipantCountLimited",
        defaultValue = false.toString()
    ) val isParticipantCountLimited: Boolean,
    @ColumnInfo(
        name = "maxParticipantCount",
        defaultValue = 0.toString()
    ) val maxParticipantCount: Int,
    @ColumnInfo(
        name = "currentParticipantCount",
        defaultValue = 0.toString()
    ) val currentParticipantCount: Int,
    @ColumnInfo(name = "isPrivate", defaultValue = false.toString()) val isPrivate: Boolean,
    @ColumnInfo(name = "isParticipant", defaultValue = false.toString()) val isParticipant: Boolean,
    @ColumnInfo(name = "isOrganiser", defaultValue = false.toString()) val isOrganiser: Boolean
)

class PersistedEventDescriptionData(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "startDate") val startDate: String,
    @ColumnInfo(name = "endDate") val endDate: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "description") val description: String,
)