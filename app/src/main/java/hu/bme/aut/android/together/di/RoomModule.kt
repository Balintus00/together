package hu.bme.aut.android.together.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.together.data.disk.database.AppDatabase
import javax.inject.Singleton

@Suppress("unused")
@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
    fun provideProfileDao(appDatabase: AppDatabase) = appDatabase.userProfileDao()

    @Provides
    fun provideIncomingEventInvitationsDao(appDatabase: AppDatabase) =
        appDatabase.eventInvitationsDao()

    @Provides
    fun provideEventShortInfoDao(appDatabase: AppDatabase) = appDatabase.eventShortInfoDao()
    
    @Provides
    fun provideEventDetailsDao(appDatabase: AppDatabase) = appDatabase.eventDataDao()

    @Provides
    fun provideEventNewsDao(appDatabase: AppDatabase) = appDatabase.eventNewsDao()

    @Provides
    fun provideEventQuestionDao(appDatabase: AppDatabase) = appDatabase.eventQuestionDao()

    @Provides
    fun provideEventAnswerDao(appDatabase: AppDatabase) = appDatabase.eventAnswerDao()

    @Provides
    fun provideQuestionAndAnswerDao(appDatabase: AppDatabase) = appDatabase.questionAndAnswerDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "together-db")
            // Currently the database is used only for caching, so this easy option seems to be perfect
            .fallbackToDestructiveMigration()
            .build()
    }

}