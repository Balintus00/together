package hu.bme.aut.android.together.features.profile.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentProfileBinding

/**
 * ProfileFragment displays the signed in user's data such as its name, username, and birth of date,
 * profile picture.
 * From this fragment the user's invitation inbox and settings can be accessed using the toolbar.
 */
class ProfileFragment : Fragment() {

    companion object {
        const val mockedInvitationCount = 1
        const val mockedName = "Botond"
        const val mockedUsername = "B0T0ND"
        const val mockedBirthOfDate = "1999.09.01."
    }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
    }

    /**
     * This function sets up the Toolbar's (with id of tbProfile) back navigation behaviour and menu.
     */
    private fun setUpToolbar() {
        setToolbarBackNavigationBehaviour()
        setUpToolbarMenu()
        setUserDataTextViews()
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

    /**
     * This function sets up the Toolbar's (with id of tbProfile) menu, the behaviour when its
     * actions are clicked, and attaches badge to the inbox action. This badge counts the incoming
     * invites.
     */
    //Already marked in the contained method, that causes this. That should be enough.
    @SuppressLint("UnsafeExperimentalUsageError")
    private fun setUpToolbarMenu() {
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
        addInvitesCountBadge()
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
    //TODO actual data (incoming invitation count) should be used later.
    @com.google.android.material.badge.ExperimentalBadgeUtils
    private fun addInvitesCountBadge() {
        BadgeDrawable.create(requireContext()).apply {
            isVisible = true
            number = mockedInvitationCount
        }.let { badge ->
            BadgeUtils.attachBadgeDrawable(badge, binding.tbProfile, R.id.actionInvitations)
        }
    }

    /**
     * This function sets the TextView's contents, which contain the name, username,
     * and birth of date.
     */
    //TODO actual should be used later.
    private fun setUserDataTextViews() {
        binding.tvProfileName.text = mockedName
        binding.tvProfileUsername.text = mockedUsername
        binding.tvProfileBirthValue.text = mockedBirthOfDate
    }
}