package hu.bme.aut.android.together.features.profile.viewmodel

import hu.bme.aut.android.together.model.presentation.ProfileData
import java.util.*

sealed class ProfileState

object Loading : ProfileState()

data class ProfileLoaded(
    val profileData: ProfileData
) : ProfileState()