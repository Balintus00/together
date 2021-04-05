package hu.bme.aut.android.together.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.android.together.model.persistence.PersistedProfileData
import hu.bme.aut.android.together.persistence.dao.ProfileDao

@Database(entities = [PersistedProfileData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}