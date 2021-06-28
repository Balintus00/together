package hu.bme.aut.android.together.hilt

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import hu.bme.aut.android.together.data.disk.model.*
import hu.bme.aut.android.together.di.RoomModule
import hu.bme.aut.android.together.persistence.dao.*
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

            override fun getEventShortInfoByEventId(eventId: Long): PersistedEventShortInfo {
                return PersistedEventShortInfo(
                    1,
                    "Coronavirus beginning party",
                    "Budapest",
                    "2020.02.14.",
                    "16:00",
                    "2020.02.14.",
                    "22:00",
                    "https://picsum.photos/200",
                    PersistedEventShortInfoType.ComingEvent.ordinal
                )
            }

            override fun getShortInfoByPersistenceOption(shortInfoPersistenceOption: Int): List<PersistedEventShortInfo> {
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
                        shortInfoPersistenceOption
                    )
                )
            }

            override fun insertCachedEventShortInfo(vararg eventShortInfo: PersistedEventShortInfo) {}

        }

    @Provides
    fun provideEventDetailsDao(@Suppress("UNUSED_PARAMETER") appDatabase: AppDatabase) =
        object : EventDataDao {
            override fun getCachedEventDetailsById(eventId: Long): PersistedEventData {
                return PersistedEventData(
                    1L,
                    "Kristóf's birtday party",
                    "https://picsum.photos/200",
                    "Tech",
                    "2021.4.20. 6:9",
                    "2021.6.9. 4:20",
                    "Budapest, Irinyi József u. 42.",
                    "Lorem ipsum dolor sit amet!",
                    false,
                    0,
                    0,
                    isPrivate = true,
                    isParticipant = true,
                    isOrganiser = true,
                    1
                )
            }

            override fun getEventTitleById(eventId: Long): String {
                return "Lorem"
            }

            override fun insertCachedEventDetails(data: PersistedEventDetails) {}

            override fun insertCachedEventDescriptionDetails(data: PersistedEventDescriptionData) {}

            override fun insertCachedEventCommunicationPagerData(data: PersistedCommunicationPagerData) {}

        }

    @Provides
    fun provideEventNewsDao(@Suppress("UNUSED_PARAMETER") appDatabase: AppDatabase) =
        object : EventNewsDao {
            override fun persistEventNews(vararg new: PersistedEventNews) {}

            override fun getCachedNewsByEventId(eventId: Long): List<PersistedEventNews> {
                return listOf()
            }

        }

    @Provides
    fun provideEventQuestionDao(@Suppress("UNUSED_PARAMETER") appDatabase: AppDatabase) =
        object : EventQuestionDao {
            override fun getEventQuestionsById(eventId: Long): List<PersistedEventQuestion> {
                return listOf()
            }

            override fun insertEventQuestion(vararg question: PersistedEventQuestion) {}

        }

    @Provides
    fun provideEventAnswerDao(@Suppress("UNUSED_PARAMETER") appDatabase: AppDatabase) =
        object : EventAnswerDao {
            override fun insertEventAnswer(vararg answer: PersistedEventAnswer) {}

        }

    @Provides
    fun provideQuestionAndAnswerDao(@Suppress("UNUSED_PARAMETER") appDatabase: AppDatabase) =
        object : QuestionAndAnswerDao {
            override fun getEventsQuestionsAndAnswers(eventId: Long): List<PersistedQuestionAndAnswer> {
                return listOf()
            }

        }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "together-db")
            .build()
    }
}