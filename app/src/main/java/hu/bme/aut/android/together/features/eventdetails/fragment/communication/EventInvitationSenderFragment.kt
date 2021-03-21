package hu.bme.aut.android.together.features.eventdetails.fragment.communication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.databinding.FragmentEventInvitationSenderBinding

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

    private fun setToolbarBehaviour() {
        with(binding.tbEventDetailsCommunication) {
            setNavigationOnClickListener {
            //TODO if the selected users' list is not empty, are you sure dialog will be shown
                findNavController().popBackStack()
            }
        }
    }

    private fun setFabBehaviour() {
        binding.fabSendInvites.setOnClickListener {
            //TODO interacting with the backend...
            findNavController().popBackStack()
        }
    }
}