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

class ProfileFragment : Fragment() {

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
        setToolbar()
    }

    //Already marked in the contained method, that causes this. That should be enough.
    @SuppressLint("UnsafeExperimentalUsageError")
    private fun setToolbar() {
        with(binding.tbProfile) {
            setNavigationIcon(R.drawable.ic_action_arrow_back)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            inflateMenu(R.menu.profile_toolbar_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.actionProfileSettingsOption -> {
                        ProfileFragmentDirections.actionProfileFragmentToSettingsFragment()
                            .let { action ->
                                findNavController().navigate(action)
                            }
                        true
                    }
                    R.id.actionInvitations -> {
                        ProfileFragmentDirections.actionProfileFragmentToEventInvitationsFragment()
                            .let { action ->
                                findNavController().navigate(action)
                            }
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }
        addInvitesCountBadge()
    }

    @com.google.android.material.badge.ExperimentalBadgeUtils
    private fun addInvitesCountBadge() {
        BadgeDrawable.create(requireContext()).apply {
            isVisible = true
            number = 1
        }.let { badge ->
            BadgeUtils.attachBadgeDrawable(badge, binding.tbProfile, R.id.actionInvitations)
        }
    }
}