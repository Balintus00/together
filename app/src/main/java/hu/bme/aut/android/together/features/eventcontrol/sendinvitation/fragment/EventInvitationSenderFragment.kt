package hu.bme.aut.android.together.features.eventcontrol.sendinvitation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.databinding.FragmentEventInvitationSenderBinding

/**
 * This fragment can be used by the event's organiser, to send invitations to the event.
 */
class EventInvitationSenderFragment : Fragment() {

    private lateinit var binding: FragmentEventInvitationSenderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventInvitationSenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        setToolbarBehaviour()
        setFabBehaviour()
    }

    /**
     * Sets the contained toolbar's onclick behaviour. After the navigation icon was clicked, the
     * user is navigated back by popping the BackStack.
     */
    private fun setToolbarBehaviour() {
        with(binding.tbEventDetailsCommunication) {
            setNavigationOnClickListener {
            //TODO if the selected users' list is not empty, are you sure dialog will be shown
                findNavController().popBackStack()
            }
        }
    }

    /**
     * Sets the contained FAB onclick behaviour. Clicking this button sends the invites to the
     * specified users, then navigates back the ser by popping the BackStack.
     */
    private fun setFabBehaviour() {
        binding.fabSendInvites.setOnClickListener {
            //TODO interacting with the backend...
            findNavController().popBackStack()
        }
    }
}