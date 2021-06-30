package hu.bme.aut.android.together.ui.screen.profile.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.domain.interactor.UserProfileInteractor
import hu.bme.aut.android.together.ui.model.ProfileData
import java.util.*
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    private val userProfileInteractor: UserProfileInteractor
) {

    suspend fun getProfile(id: Long): ProfileData = withIOContext {
        // TODO handle IOException; should be rewritten in v0.3 (refreshing logic)
        userProfileInteractor.refreshUserProfileById(id)
        userProfileInteractor.loadUserProfileById(id).let {
            ProfileData(
                it.name,
                it.username,
                createPresentedDateFormat(it.dateOfBirth),
                it.profileImageUri,
                it.incomingInvitesCount
            )
        }
    }

    private fun createPresentedDateFormat(representedDate: Date): String {
        with(Calendar.getInstance()) {
            time = representedDate
            return "${get(Calendar.YEAR)}.${get(Calendar.MONTH) + 1}.${get(Calendar.DAY_OF_MONTH)}"
        }
    }
}