package hu.bme.aut.android.together.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.together.persistence.database.AppDatabase
import javax.inject.Singleton

@Suppress("unused")
@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
    fun provideProfileDao(appDatabase: AppDatabase) = appDatabase.profileDao()

    @Provides
    fun provideIncomingEventInvitationsDao(appDatabase: AppDatabase) =
        appDatabase.eventInvitationsDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "together-db")
            .build()
    }

}