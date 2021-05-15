package hu.bme.aut.android.together.features.profile.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import com.bumptech.glide.Glide
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentProfileBinding
import hu.bme.aut.android.together.features.profile.viewmodel.Loading
import hu.bme.aut.android.together.features.profile.viewmodel.ProfileLoaded
import hu.bme.aut.android.together.features.profile.viewmodel.ProfileState
import hu.bme.aut.android.together.features.profile.viewmodel.ProfileViewModel
import hu.bme.aut.android.together.model.presentation.ProfileData

/**
 * ProfileFragment displays the signed in user's data such as its name, username, and birth of date,
 * profile picture.
 * From this fragment the user's invitation inbox and settings can be accessed using the toolbar.
 */
@AndroidEntryPoint
class ProfileFragment : RainbowCakeFragment<ProfileState, ProfileViewModel>() {

    companion object {
        private const val MOCKED_PROFILE_ID = 1L
    }

    private lateinit var binding: FragmentProfileBinding

    private val profileViewModel : ProfileViewModel by viewModels()

    override fun provideViewModel() = profileViewModel

    override fun render(viewState: ProfileState) {
        when (viewState) {
            is Loading -> {
                setUiWhenNoDataIsLoaded()
                binding.pbProfileLoadingIndicator.isVisible = true
                binding.clContainer.isVisible = false
            }
            is ProfileLoaded -> {
                binding.pbProfileLoadingIndicator.isVisible = false
                setUiOnProfileLoaded(viewState.profileData)
                binding.clContainer.isVisible = true
            }
        }.exhaustive
    }

    private fun setUiWhenNoDataIsLoaded() {
        resetToolbar()
    }

    private fun resetToolbar() {
        binding.tbProfile.menu.clear()
    }

    private fun setUiOnProfileLoaded(profileData: ProfileData) {
        setUpToolbarMenu(profileData.incomingInvitesCount)
        setUserDataTextViews(profileData)
        setProfileImage(profileData.profileImageUri)
    }

    /**
     * This function sets up the Toolbar's (with id of tbProfile) menu, the behaviour when its
     * actions are clicked, and attaches badge to the inbox action. This badge counts the incoming
     * invites.
     */
    //Already marked in the contained method, that causes this. That should be enough.
    @SuppressLint("UnsafeExperimentalUsageError")
    private fun setUpToolbarMenu(incomingInvitesCount: Int) {
        with(binding.tbProfile) {
            inflateMenu(R.menu.profile_toolbar_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.actionProfileSettingsOption -> {
                        navigateToSettingsScreen()
                        true
                    }
                    R.id.actionInvitations -> {
                        navigateToInvitationInboxScreen()
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }
        addInvitesCountBadge(incomingInvitesCount)
    }

    private fun navigateToSettingsScreen() {
        ProfileFragmentDirections.actionProfileFragmentToSettingsFragment()
            .let { action ->
                binding.tbProfile.findNavController().navigate(action)
            }
    }

    private fun navigateToInvitationInboxScreen() {
        ProfileFragmentDirections.actionProfileFragmentToEventInvitationsFragment()
            .let { action ->
                binding.tbProfile.findNavController().navigate(action)
            }
    }

    /**
     * Adds badge to the Toolbar's (with id of tbProfile) menu's inbox action (with id of
     * R.id.actionInvitations) a badge, that counts the incoming the invites of the user.
     */
    @com.google.android.material.badge.ExperimentalBadgeUtils
    private fun addInvitesCountBadge(incomingInvitesCount: Int) {
        BadgeDrawable.create(requireContext()).apply {
            isVisible = true
            number = incomingInvitesCount
        }.let { badge ->
            BadgeUtils.attachBadgeDrawable(badge, binding.tbProfile, R.id.actionInvitations)
        }
    }

    /**
     * This function sets the TextView's contents, which contain the name, username,
     * and birth of date.
     */
    private fun setUserDataTextViews(profileData: ProfileData) {
        binding.tvProfileName.text = profileData.name
        binding.tvProfileUsername.text = profileData.username
        binding.tvProfileBirthValue.text = profileData.dateOfBirth
    }

    private fun setProfileImage(imageUri: String) {
        Glide.with(binding.civProfileImage).load(imageUri).into(binding.civProfileImage)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarBackNavigationBehaviour()
    }

    /**
     * This function sets the Toolbar's (with id of tbProfile) navigation action's behaviour.
     * When the navigation action is clicked, the BackStack will be popped.
     */
    private fun setToolbarBackNavigationBehaviour() {
        with(binding.tbProfile) {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadProfileData(MOCKED_PROFILE_ID)
    }
}