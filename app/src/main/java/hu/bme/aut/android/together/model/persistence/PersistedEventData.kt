package hu.bme.aut.android.together.model.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PersistedEventData(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title", defaultValue = "") val title: String,
    @ColumnInfo(name = "imageUrl", defaultValue = "") val imageUrl: String,
    @ColumnInfo(name = "category", defaultValue = "") val category: String,
    @ColumnInfo(name = "startDate", defaultValue = "") val startDate: String,
    @ColumnInfo(name = "endDate", defaultValue = "") val endDate: String,
    @ColumnInfo(name = "location", defaultValue = "") val location: String,
    @ColumnInfo(name = "description", defaultValue = "") val description: String,
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
    @ColumnInfo(name = "isOrganiser", defaultValue = false.toString()) val isOrganiser: Boolean,
    @ColumnInfo(name = "organiserQuestionCount", defaultValue = 0.toString()) val organiserQuestionCount: Int
)

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

class PersistedEventDescriptionData(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "startDate") val startDate: String,
    @ColumnInfo(name = "endDate") val endDate: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "description") val description: String,
)

class PersistedCommunicationPagerData(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val eventTitle: String,
    @ColumnInfo(name = "isOrganiser") val isOrganiser: Boolean,
    @ColumnInfo(name = "organiserQuestionCount") val organiserQuestionCount: Int
)