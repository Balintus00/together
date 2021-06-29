package hu.bme.aut.android.together.incominginvitations

import hu.bme.aut.android.together.domain.interactor.IncomingEventInvitationsInteractor
import hu.bme.aut.android.together.mockito.any
import hu.bme.aut.android.together.domain.model.DomainEventInvitation
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventInvitationsRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class IncomingInvitationsInteractorTest {

    private val mockNetworkDataSource: NetworkDataSource =
        Mockito.mock(NetworkDataSource::class.java)

    private val mockEventInvitationsRepository: EventInvitationsRepository =
        Mockito.mock(EventInvitationsRepository::class.java)

    private lateinit var incomingEventInvitationsInteractor: IncomingEventInvitationsInteractor

    @Before
    fun setUp() {
        incomingEventInvitationsInteractor = IncomingEventInvitationsInteractor(
            mockNetworkDataSource,
            mockEventInvitationsRepository
        )
    }

    @Test
    fun checkCachingIfNetworkWasAvailable() {
        val exampleProfileId = 1L
        val exampleInvitationList = listOf(
            DomainEventInvitation(1L, "Lorem", "Ipsum", "Dolor sit amet.")
        )
        Mockito.`when`(mockNetworkDataSource.getIncomingInvitesById(exampleProfileId)).thenReturn(
            exampleInvitationList
        )
        incomingEventInvitationsInteractor.getUsersIncomingInvites(exampleProfileId)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1))
            .getIncomingInvitesById(exampleProfileId)
        Mockito.verify(mockEventInvitationsRepository, Mockito.times(1))
            .saveIncomingEventInvitations(*exampleInvitationList.toTypedArray())
        Mockito.verify(mockEventInvitationsRepository, Mockito.never())
            .loadIncomingEventInvitations()
    }

    @Test
    fun checkCachingIfNetworkWasUnavailable() {
        val exampleProfileId = 1L
        val exampleInvitationList = listOf(
            DomainEventInvitation(1L, "Lorem", "Ipsum", "Dolor sit amet.")
        )
        Mockito.`when`(mockNetworkDataSource.getIncomingInvitesById(exampleProfileId))
            .thenReturn(null)
        Mockito.`when`(mockEventInvitationsRepository.loadIncomingEventInvitations())
            .thenReturn(exampleInvitationList)
        incomingEventInvitationsInteractor.getUsersIncomingInvites(exampleProfileId)
        Mockito.verify(mockNetworkDataSource, Mockito.times(1)).getIncomingInvitesById(1L)
        Mockito.verify(mockEventInvitationsRepository, Mockito.never())
            .saveIncomingEventInvitations(any())
        Mockito.verify(mockEventInvitationsRepository, Mockito.times(1))
            .loadIncomingEventInvitations()
    }

}