package hu.bme.aut.android.together.model.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersistedEventShortInfo(
    @PrimaryKey val eventId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "startDate") val startDate: String,
    @ColumnInfo(name = "startTime") val startTime: String,
    @ColumnInfo(name = "endDate") val endDate: String,
    @ColumnInfo(name = "endTime") val endTime: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "isComing") val isComing: Boolean,
    @ColumnInfo(name = "cachingType") val cachingType: Int
)

enum class PersistedEventShortInfoType {
    ComingEvent, PastEvent, ResultEvent
}