package hu.bme.aut.android.together.profile

import hu.bme.aut.android.together.features.profile.interactor.ProfileInteractor
import hu.bme.aut.android.together.model.domain.DomainProfileData
import hu.bme.aut.android.together.network.NetworkDataSource
import hu.bme.aut.android.together.persistence.repository.ProfileRepository
import hu.bme.aut.android.together.profile.mockito.any
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*

@RunWith(JUnit4::class)
class ProfileInteractorTest {

    private val mockNetworkDataSource: NetworkDataSource = mock(NetworkDataSource::class.java)

    private val mockProfileRepository: ProfileRepository = mock(ProfileRepository::class.java)

    private lateinit var profileInteractor: ProfileInteractor

    @Before
    fun setUp() {
        profileInteractor = ProfileInteractor(mockNetworkDataSource, mockProfileRepository)
    }

    @Test
    fun checkCachingIfNetworkWasAvailable() {
        val exampleProfileId = 1L
        val exampleDomainProfileData = DomainProfileData(
            1L,
            "Botond",
            "B0T0ND",
            Date(),
            "https://picsum.photos/200",
            1
        )
        `when`(mockNetworkDataSource.getUserProfileById(exampleProfileId)).thenReturn(
            exampleDomainProfileData
        )
        profileInteractor.getProfileDataById(1L)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1)).getUserProfileById(exampleProfileId)
        Mockito.verify(mockProfileRepository, Mockito.times(1))
            .saveProfileData(exampleDomainProfileData)
        Mockito.verify(mockProfileRepository, Mockito.never()).loadProfileData(Mockito.anyLong())
    }

    @Test
    fun checkCachingIfNetworkWasUnavailable() {
        val exampleProfileId = 1L
        val exampleDomainProfileData = DomainProfileData(
            1L,
            "Botond",
            "B0T0ND",
            Date(),
            "https://picsum.photos/200",
            1
        )
        `when`(mockNetworkDataSource.getUserProfileById(exampleProfileId)).thenReturn(null)
        `when`(mockProfileRepository.loadProfileData(1L)).thenReturn(exampleDomainProfileData)
        profileInteractor.getProfileDataById(1L)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1)).getUserProfileById(exampleProfileId)
        Mockito.verify(mockProfileRepository, Mockito.never())
            .saveProfileData(any(DomainProfileData::class.java))
        Mockito.verify(mockProfileRepository, Mockito.times(1)).loadProfileData(1L)
    }

}