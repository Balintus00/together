package hu.bme.aut.android.together.ui.screen.profile.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.domain.interactor.ProfileInteractor
import hu.bme.aut.android.together.ui.model.ProfileData
import java.util.*
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    private val profileInteractor: ProfileInteractor
) {

    suspend fun getProfile(id: Long): ProfileData = withIOContext {
        profileInteractor.getProfileDataById(id).let {
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