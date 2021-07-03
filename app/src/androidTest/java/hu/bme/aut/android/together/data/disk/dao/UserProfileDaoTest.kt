package hu.bme.aut.android.together.data.disk.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import hu.bme.aut.android.together.data.disk.database.AppDatabase
import hu.bme.aut.android.together.data.disk.model.PersistedUserProfile
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class UserProfileDaoTest {
    private lateinit var userProfileDao: UserProfileDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        ApplicationProvider.getApplicationContext<Context>().let { context ->
            db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
            userProfileDao = db.userProfileDao()
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @SmallTest
    @Throws(Exception::class)
    fun insertUserProfile_insertsPersistedUserProfile_ifCalledWithOneParameter(): Unit =
        runBlocking {
            val examplePersistedUserProfile = PersistedUserProfile(
                1L,
                "Botond",
                "B0T0ND",
                Date(),
                "https://picsum.photos/200",
                1
            )

            userProfileDao.insertUserProfile(examplePersistedUserProfile)

            val result = userProfileDao.getUserProfileById(examplePersistedUserProfile.id)
            assertThat(result == examplePersistedUserProfile)
        }

    @Test
    @SmallTest
    @Throws(Exception::class)
    fun insertUserProfile_insertsTwoPersistedUserProfile_ifCalledWithTwoParameter(): Unit =
        runBlocking {
            val examplePersistedUserProfile1 = PersistedUserProfile(
                1L,
                "Botond",
                "B0T0ND",
                Date(),
                "https://picsum.photos/200",
                1
            )
            val examplePersistedUserProfile2 = PersistedUserProfile(
                1L,
                "Botond",
                "B0T0ND",
                Date(),
                "https://picsum.photos/200",
                1
            )

            userProfileDao.insertUserProfile(
                examplePersistedUserProfile1,
                examplePersistedUserProfile2
            )

            val result1 = userProfileDao.getUserProfileById(examplePersistedUserProfile1.id)
            val result2 = userProfileDao.getUserProfileById(examplePersistedUserProfile2.id)
            assertThat(result1 == examplePersistedUserProfile1)
            assertThat(result2 == examplePersistedUserProfile2)
        }

    @Test
    @SmallTest
    @Throws(Exception::class)
    fun insertUserProfile_overwritesEntity_ifConflictHappensAtInsert(): Unit = runBlocking {
        val commonUserProfileId = 1L
        val examplePersistedUserProfileV1 = PersistedUserProfile(
            commonUserProfileId,
            "Botond",
            "B0T0ND",
            Date(),
            "https://picsum.photos/200",
            1
        )
        val examplePersistedUserProfileV2 = PersistedUserProfile(
            commonUserProfileId,
            "Botond2",
            "B0T0ND2",
            Date(),
            "https://picsum.photos/200",
            2
        )
        userProfileDao.insertUserProfile(examplePersistedUserProfileV1)
        val resultV1 = userProfileDao.getUserProfileById(commonUserProfileId)
        assertThat(resultV1 == examplePersistedUserProfileV1)

        userProfileDao.insertUserProfile(examplePersistedUserProfileV2)
        val resultV2 = userProfileDao.getUserProfileById(commonUserProfileId)

        assertThat(resultV2 == examplePersistedUserProfileV2)
    }
}