package hu.bme.aut.android.together.data.disk.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.android.together.data.disk.dao.*
import hu.bme.aut.android.together.data.disk.model.*

@Database(
    entities = [
        PersistedEventData::class, PersistedEventInvitation::class, PersistedEventNews::class,
        PersistedEventQuestion::class, PersistedEventAnswer::class,PersistedEventShortInfo::class,
        PersistedProfileData::class],
    version = 8
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventAnswerDao(): EventAnswerDao

    abstract fun eventDataDao(): EventDataDao

    abstract fun eventInvitationsDao(): EventInvitationsDao

    abstract fun eventNewsDao(): EventNewsDao

    abstract fun eventQuestionDao(): EventQuestionDao

    abstract fun eventShortInfoDao(): EventShortInfoDao

    abstract fun profileDao(): ProfileDao

    abstract fun questionAndAnswerDao(): QuestionAndAnswerDao
}