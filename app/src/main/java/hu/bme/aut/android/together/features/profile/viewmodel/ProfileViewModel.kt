package hu.bme.aut.android.together.features.profile.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.features.profile.presenter.ProfilePresenter
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profilePresenter: ProfilePresenter
) : RainbowCakeViewModel<ProfileState>(Loading) {

    fun loadProfileData(id: Long) = execute {
        viewState = Loading
        profilePresenter.getProfile(id).let {
            viewState = ProfileLoaded(it)
        }
    }

}