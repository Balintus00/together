package hu.bme.aut.android.together.ui.screen.profile.viewmodel

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.google.common.truth.Truth
import hu.bme.aut.android.together.ui.screen.profile.presenter.ProfilePresenter
import hu.bme.aut.android.together.ui.model.ProfileData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ProfileViewModelTest : ViewModelTest() {

    private val profilePresenterMock : ProfilePresenter = mock(ProfilePresenter::class.java)

    private lateinit var profileViewModel: ProfileViewModel

    @Before
    fun setUp() {
        profileViewModel = ProfileViewModel(profilePresenterMock)
    }

    @Test
    fun checkInitialViewStateIsLoadingState() {
        Truth.assertThat(profileViewModel.state.value is Loading)
    }

    @Test
    fun checkViewStateAfterSuccessfulLoading() : Unit = runBlockingTest {
        `when`(profilePresenterMock.getProfile(1L)).thenReturn(
            ProfileData(
            "Botond",
            "B0T0ND",
            "1999.9.1",
            "https://picsum.photos/200",
            1
        )
        )
        profileViewModel.observeStateAndEvents { stateObserver, _ ->
            profileViewModel.loadProfileData(1L)
            stateObserver.assertObserved(Loading, ProfileLoaded(
                ProfileData(
                "Botond",
                "B0T0ND",
                "1999.9.1",
                "https://picsum.photos/200",
                1
            )
            ))
        }
    }

}