package hu.bme.aut.android.together.data.disk.datasource

import com.google.common.truth.Truth.assertThat
import hu.bme.aut.android.together.data.disk.dao.UserProfileDao
import hu.bme.aut.android.together.data.disk.model.PersistedUserProfile
import hu.bme.aut.android.together.domain.model.DomainUserProfile
import hu.bme.aut.android.together.mockito.capture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.*
import org.mockito.Mockito.mock
import org.mockito.internal.verification.VerificationModeFactory.times
import java.util.*

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class UserProfileDatasourceTest {

    private val mockUserProfileDao: UserProfileDao = mock(UserProfileDao::class.java)

    private lateinit var userProfileDatasource: UserProfileDatasource

    @Before
    fun setUp() {
        userProfileDatasource = UserProfileDatasource(mockUserProfileDao)
    }

    @Test
    fun getUserProfileById_returnsCorrectDomainUserProfile_ifDaoSuccessfullyReturnsSearchedData() =
        runBlockingTest {
            val examplePersistedUserProfile = PersistedUserProfile(
                1L,
                "Botond",
                "B0T0ND",
                Date(),
                "https://picsum.photos/200",
                1
            )
            given(mockUserProfileDao.getUserProfileById(examplePersistedUserProfile.id))
                .willReturn(examplePersistedUserProfile)

            val result = userProfileDatasource.getUserProfileById(examplePersistedUserProfile.id)

            then(mockUserProfileDao)
                .should(times(1))
                .getUserProfileById(examplePersistedUserProfile.id)
            val expectedDomainUserProfile = DomainUserProfile(
                1L,
                "Botond",
                "B0T0ND",
                Date(),
                "https://picsum.photos/200",
                1
            )
            assertThat(result == expectedDomainUserProfile)
        }

    @Test
    fun getUserProfileById_returnsNull_ifDaoReturnsNull() = runBlockingTest {
        val exampleUserProfileId = 1L
        given(mockUserProfileDao.getUserProfileById(exampleUserProfileId))
            .willReturn(null)

        val result = userProfileDatasource.getUserProfileById(exampleUserProfileId)

        then(mockUserProfileDao)
            .should(times(1))
            .getUserProfileById(exampleUserProfileId)
        assertThat(result == null)
    }

    @Test
    fun persistUserProfile_passesNothingToPersistOrDoesNotCallsPersist_ifNoParameterWasPassed() =
        runBlockingTest {
            userProfileDatasource.persistUserProfile()

            val passedVarArgsToDao: ArgumentCaptor<PersistedUserProfile> =
                ArgumentCaptor.forClass(PersistedUserProfile::class.java)
            then(mockUserProfileDao)
                .should(atMost(1))
                .insertUserProfile(capture(passedVarArgsToDao))
            val expectedPassedArgumentList = listOf<PersistedUserProfile>()
            assertThat(expectedPassedArgumentList == passedVarArgsToDao.allValues)
        }

    @Test
    fun persistUserProfile_passesTheDataToDaoToPersistIt_ifOneParameterWasProvided() =
        runBlockingTest {
            val exampleDomainUserProfile = DomainUserProfile(
                1L,
                "Botond",
                "B0T0ND",
                Date(),
                "https://picsum.photos/200",
                1
            )

            userProfileDatasource.persistUserProfile(exampleDomainUserProfile)

            val passedVarArgsToDao: ArgumentCaptor<PersistedUserProfile> =
                ArgumentCaptor.forClass(PersistedUserProfile::class.java)
            then(mockUserProfileDao)
                .should(times(1))
                .insertUserProfile(capture(passedVarArgsToDao))
            val expectedPassedArgumentList = listOf(
                PersistedUserProfile(
                    1L,
                    "Botond",
                    "B0T0ND",
                    Date(),
                    "https://picsum.photos/200",
                    1
                )
            )
            assertThat(expectedPassedArgumentList == passedVarArgsToDao.allValues)
        }

    @Test
    fun persistUserProfile_passesTheDataToDaoToPersistIt_ifTwoParameterWasProvided() =
        runBlockingTest {
            val exampleDomainUserProfile1 = DomainUserProfile(
                1L,
                "Botond",
                "B0T0ND",
                Date(),
                "https://picsum.photos/200",
                1
            )
            val exampleDomainUserProfile2 = DomainUserProfile(
                2L,
                "Botond",
                "B0T0ND",
                Date(),
                "https://picsum.photos/200",
                1
            )

            userProfileDatasource.persistUserProfile(
                exampleDomainUserProfile1,
                exampleDomainUserProfile2
            )

            val passedVarArgsToDao: ArgumentCaptor<PersistedUserProfile> =
                ArgumentCaptor.forClass(PersistedUserProfile::class.java)
            then(mockUserProfileDao)
                .should(times(1))
                .insertUserProfile(capture(passedVarArgsToDao))
            val expectedPassedArgumentList = listOf(
                PersistedUserProfile(
                    1L,
                    "Botond",
                    "B0T0ND",
                    Date(),
                    "https://picsum.photos/200",
                    1
                ),
                PersistedUserProfile(
                    2L,
                    "Botond",
                    "B0T0ND",
                    Date(),
                    "https://picsum.photos/200",
                    1
                )
            )
            assertThat(expectedPassedArgumentList == passedVarArgsToDao.allValues)
        }
}