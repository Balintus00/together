package hu.bme.aut.android.together.data.disk.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.android.together.data.disk.dao.*
import hu.bme.aut.android.together.data.disk.model.*
import hu.bme.aut.android.together.data.disk.typeconverter.DateConverter

@Database(
    entities = [
        PersistedEventData::class, PersistedEventInvitation::class, PersistedEventNews::class,
        PersistedEventQuestion::class, PersistedEventAnswer::class,PersistedEventShortInfo::class,
        PersistedUserProfile::class],
    version = 9
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventAnswerDao(): EventAnswerDao

    abstract fun eventDataDao(): EventDataDao

    abstract fun eventInvitationsDao(): EventInvitationsDao

    abstract fun eventNewsDao(): EventNewsDao

    abstract fun eventQuestionDao(): EventQuestionDao

    abstract fun eventShortInfoDao(): EventShortInfoDao

    abstract fun questionAndAnswerDao(): QuestionAndAnswerDao

    abstract fun userProfileDao(): UserProfileDao
}