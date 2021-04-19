package hu.bme.aut.android.together.model.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PersistedEventDetails(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "startDate") val startDate: String,
    @ColumnInfo(name = "endDate") val endDate: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "isParticipantCountLimited") val isParticipantCountLimited: Boolean,
    @ColumnInfo(name = "maxParticipantCount") val maxParticipantCount: Int,
    @ColumnInfo(name = "currentParticipantCount") val currentParticipantCount: Int,
    @ColumnInfo(name = "isPrivate") val isPrivate: Boolean,
    @ColumnInfo(name = "isParticipant") val isParticipant: Boolean,
    @ColumnInfo(name = "isOrganiser") val isOrganiser: Boolean
)