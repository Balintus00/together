package hu.bme.aut.android.together.ui.screen.profile.viewmodel

import hu.bme.aut.android.together.ui.model.ProfileData

sealed class ProfileState

object Loading : ProfileState()

data class ProfileLoaded(
    val profileData: ProfileData
) : ProfileState()