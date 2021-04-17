package hu.bme.aut.android.together.hilt

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import hu.bme.aut.android.together.di.RoomModule
import hu.bme.aut.android.together.model.persistence.PersistedEventInvitation
import hu.bme.aut.android.together.model.persistence.PersistedEventShortInfo
import hu.bme.aut.android.together.model.persistence.PersistedProfileData
import hu.bme.aut.android.together.persistence.dao.EventInvitationsDao
import hu.bme.aut.android.together.persistence.dao.EventShortInfoDao
import hu.bme.aut.android.together.persistence.dao.ProfileDao
import hu.bme.aut.android.together.persistence.database.AppDatabase
import javax.inject.Singleton

@Suppress("unused")
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RoomModule::class]
)
class FakeRoomModule {

    @Provides
    fun provideProfileDao(@Suppress("UNUSED_PARAMETER") appDatabase: AppDatabase) =
        object : ProfileDao {
            override fun getProfileDataById(id: Long) = PersistedProfileData(
                1,
                "Botond",
                "B0T0ND",
                "1999.9.1",
                "https://picsum.photos/200",
                1
            )

            override fun insertProfileData(profile: PersistedProfileData) {}

        }

    @Provides
    fun provideIncomingEventInvitationsDao(@Suppress("UNUSED_PARAMETER") appDatabase: AppDatabase) =
        object : EventInvitationsDao {
            override fun getCachedIncomingEventInvitations(): List<PersistedEventInvitation> {
                return listOf(
                    PersistedEventInvitation(
                        1,
                        "Come join my birthday party!",
                        "KR1ST0F",
                        "Lorem ipsum dolor sit amet!"
                    )
                )
            }

            override fun insertIncomingEventInvitations(vararg invitations: PersistedEventInvitation) {}

        }

    @Provides
    fun provideEventShortInfoDao(@Suppress("UNUSED_PARAMETER") appDatabase: AppDatabase) =
        object : EventShortInfoDao {
            override fun getCachedComingEventShortInfo(): List<PersistedEventShortInfo> {
                return listOf(
                    PersistedEventShortInfo(
                        1,
                        "Coronavirus beginning party",
                        "Budapest",
                        "2020.02.14.",
                        "16:00",
                        "2020.02.14.",
                        "22:00",
                        "https://picsum.photos/200",
                        true
                    )
                )
            }

            override fun getPastComingEventShortInfo(): List<PersistedEventShortInfo> {
                return listOf(
                    PersistedEventShortInfo(
                        1,
                        "Coronavirus beginning party",
                        "Budapest",
                        "2020.02.14.",
                        "16:00",
                        "2020.02.14.",
                        "22:00",
                        "https://picsum.photos/200",
                        true
                    )
                )
            }

            override fun insertCachedEventShortInfo(vararg eventShortInfo: PersistedEventShortInfo) {}

        }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "together-db")
            .build()
    }
}