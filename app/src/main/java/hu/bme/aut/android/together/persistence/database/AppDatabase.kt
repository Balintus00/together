package hu.bme.aut.android.together.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.android.together.model.persistence.*
import hu.bme.aut.android.together.persistence.dao.*

@Database(
    entities = [PersistedProfileData::class, PersistedEventInvitation::class,
        PersistedEventShortInfo::class, PersistedEventData::class, PersistedEventNews::class,
        PersistedEventQuestion::class, PersistedEventAnswer::class],
    version = 8
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    abstract fun eventInvitationsDao(): EventInvitationsDao

    abstract fun eventShortInfoDao(): EventShortInfoDao

    abstract fun eventDataDao(): EventDataDao

    abstract fun eventNewsDao(): EventNewsDao

    abstract fun eventQuestionDao(): EventQuestionDao

    abstract fun eventAnswerDao(): EventAnswerDao

    abstract fun questionAndAnswerDao(): QuestionAndAnswerDao
}