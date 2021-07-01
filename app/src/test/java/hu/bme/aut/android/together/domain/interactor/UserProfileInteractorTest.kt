package hu.bme.aut.android.together.domain.interactor

import hu.bme.aut.android.together.data.disk.datasource.UserProfileDatasource
import hu.bme.aut.android.together.domain.model.DomainUserProfile
import hu.bme.aut.android.together.data.network.NetworkDataSource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.*
import hu.bme.aut.android.together.mockito.any
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.fail
import java.io.IOException
import java.util.*

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class UserProfileInteractorTest {

    private val mockNetworkDataSource: NetworkDataSource = mock(NetworkDataSource::class.java)

    private val mockUserProfileDatasource: UserProfileDatasource =
        mock(UserProfileDatasource::class.java)

    private lateinit var userProfileInteractor: UserProfileInteractor

    @Before
    fun setUp() {
        userProfileInteractor =
            UserProfileInteractor(mockNetworkDataSource, mockUserProfileDatasource)
    }

    @Test
    fun loadUserProfileById_returnsDomainUserProfile_ifGivenProfileIsNonNull() = runBlockingTest {
        val exampleProfileId = 1L
        val exampleDomainProfileData = DomainUserProfile(
            1L,
            "Botond",
            "B0T0ND",
            Date(),
            "https://picsum.photos/200",
            1
        )
        given(mockUserProfileDatasource.getUserProfileById(exampleProfileId))
            .willReturn(exampleDomainProfileData)

        val result = userProfileInteractor.loadUserProfileById(exampleProfileId)

        then(mockUserProfileDatasource)
            .should(times(1))
            .getUserProfileById(exampleProfileId)
        assertThat(result == exampleDomainProfileData)
    }

    @Test
    fun loadUserProfileById_throwsIOException_ifGivenProfileNull() = runBlockingTest {
        val exampleProfileId = 1L
        var expectedException: Exception? = null
        given(mockUserProfileDatasource.getUserProfileById(exampleProfileId))
            .willReturn(null)

        try {
            userProfileInteractor.loadUserProfileById(exampleProfileId)
            fail("IOException should be thrown.")
        } catch (e: Exception) {
            expectedException = e
        }

        then(mockUserProfileDatasource)
            .should(times(1))
            .getUserProfileById((exampleProfileId))
        assertThat(expectedException is IOException)
    }

    @Test
    fun refreshUserProfileById_cachesResults_ifNetworkCallIsSuccessful() = runBlockingTest {
        val exampleProfileId = 1L
        val exampleDomainProfileData = DomainUserProfile(
            1L,
            "Botond",
            "B0T0ND",
            Date(),
            "https://picsum.photos/200",
            1
        )
        given(mockNetworkDataSource.getUserProfileById(exampleProfileId))
            .willReturn(exampleDomainProfileData)

        userProfileInteractor.refreshUserProfileById(exampleProfileId)

        then(mockNetworkDataSource)
            .should(times(1))
            .getUserProfileById(exampleProfileId)
        then(mockUserProfileDatasource)
            .should(times(1))
            .persistUserProfile(exampleDomainProfileData)
    }

    @Test
    fun refreshUserProfileById_throwsIOExceptionAndDoesNotCaches_IfNetworkCallIsUnsuccessful() =
        runBlockingTest {
            val exampleProfileId = 1L
            var expectedException: Exception? = null
            given(mockNetworkDataSource.getUserProfileById(exampleProfileId))
                .willReturn(null)

            try {
                userProfileInteractor.refreshUserProfileById(exampleProfileId)
            } catch (e: Exception) {
                expectedException = e
            }

            then(mockNetworkDataSource)
                .should(times(1))
                .getUserProfileById(exampleProfileId)
            then(mockUserProfileDatasource)
                .should(never())
                .persistUserProfile(any())
            assertThat(expectedException is IOException)
        }

}