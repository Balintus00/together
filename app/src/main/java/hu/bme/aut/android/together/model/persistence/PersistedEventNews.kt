package hu.bme.aut.android.together.model.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PersistedEventNews(
    @PrimaryKey val id: Long,
    val title: String,
    val author: String,
    val message: String,
    val eventId: Long
)