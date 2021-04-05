package hu.bme.aut.android.together.di

import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.dagger.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import hu.bme.aut.android.together.features.incomiginvitations.viewmodel.IncomingEventInvitationsViewModel
import hu.bme.aut.android.together.features.profile.viewmodel.ProfileViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomingEventInvitationsViewModel::class)
    abstract fun bindIncomingEventInvitationsViewModel(viewModel: IncomingEventInvitationsViewModel): ViewModel
}
