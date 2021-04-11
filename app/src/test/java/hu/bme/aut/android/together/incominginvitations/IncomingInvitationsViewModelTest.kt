package hu.bme.aut.android.together.incominginvitations

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.google.common.truth.Truth
import hu.bme.aut.android.together.features.incomiginvitations.presenter.IncomingEventInvitationsPresenter
import hu.bme.aut.android.together.features.incomiginvitations.viewmodel.IncomingEventInvitationsLoaded
import hu.bme.aut.android.together.features.incomiginvitations.viewmodel.IncomingEventInvitationsViewModel
import hu.bme.aut.android.together.features.incomiginvitations.viewmodel.Loading
import hu.bme.aut.android.together.model.presentation.EventInvitation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class IncomingInvitationsViewModelTest : ViewModelTest() {

    private val incomingInvitationsPresenterMock: IncomingEventInvitationsPresenter =
        Mockito.mock(IncomingEventInvitationsPresenter::class.java)

    private lateinit var incomingEventInvitationsViewModel: IncomingEventInvitationsViewModel

    @Before
    fun setUp() {
        incomingEventInvitationsViewModel =
            IncomingEventInvitationsViewModel(incomingInvitationsPresenterMock)
    }

    @Test
    fun checkInitialViewStateIsLoadingState() {
        Truth.assertThat(incomingEventInvitationsViewModel.state.value is Loading)
    }

    @Test
    fun checkViewStateAfterSuccessfulLoading(): Unit = runBlockingTest {
        Mockito.`when`(incomingInvitationsPresenterMock.getIncomingInvites(1L)).thenReturn(
            listOf(
                EventInvitation("Lorem", "Ipsum", "Dolor sit amet.")
            )
        )
        incomingEventInvitationsViewModel.observeStateAndEvents { stateObserver, _ ->
            incomingEventInvitationsViewModel.loadIncomingInvitesByProfileId(1L)
            stateObserver.assertObserved(
                Loading, IncomingEventInvitationsLoaded(
                    listOf(
                        EventInvitation("Lorem", "Ipsum", "Dolor sit amet.")
                    )
                )
            )
        }
    }

}