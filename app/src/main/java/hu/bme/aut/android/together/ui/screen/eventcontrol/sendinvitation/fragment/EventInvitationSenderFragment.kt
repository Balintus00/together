package hu.bme.aut.android.together.ui.screen.eventcontrol.sendinvitation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentEventInvitationSenderBinding
import hu.bme.aut.android.together.ui.screen.eventcontrol.sendinvitation.viewmodel.*
import hu.bme.aut.android.together.ui.model.EventShortInfo

/**
 * This fragment can be used by the event's organiser, to send invitations to the event.
 * TODO this feature could be better, if could do the following:
 *  - Displaying the sent invitations
 *  - Instead of a TextView a RecyclerView should be used to show the currently added users
 *  - From this RecyclerView elements could be removed with a button or by swiping gestures
 */
@AndroidEntryPoint
class EventInvitationSenderFragment :
    RainbowCakeFragment<EventInvitationSenderState, EventInvitationSenderViewModel>() {

    private val args: EventInvitationSenderFragmentArgs by navArgs()

    private val eventInvitationSenderViewModel: EventInvitationSenderViewModel by viewModels()

    private var _binding: FragmentEventInvitationSenderBinding? = null

    private val binding get() = _binding!!

    override fun provideViewModel(): EventInvitationSenderViewModel = eventInvitationSenderViewModel

    override fun render(viewState: EventInvitationSenderState) {
        when (viewState) {
            is Loading -> {
                displayLoadingUI()
            }
            is EventInformationLoaded -> {
                representLoadedEventInformation(viewState.shortInfo)
            }
            is InvitationSendingOperationEnded -> {
                onSendingOperationEnded(viewState.wasSuccessful)
            }
        }.exhaustive
    }

    private fun displayLoadingUI() {
        with(binding) {
            clContent.isVisible = false
            cpiEventInvitationSenderLoading.isVisible = true
        }
    }

    private fun displayLoadedUI() {
        with(binding) {
            cpiEventInvitationSenderLoading.isVisible = false
            clContent.isVisible = true
        }
    }

    private fun representLoadedEventInformation(information: EventShortInfo) {
        with(binding) {
            tvEventStartDate.text = information.startDate
            tvEventLocation.text = information.location
            tvEventTitle.text = information.name
        }
        displayLoadedUI()
    }

    private fun onSendingOperationEnded(wasSuccessful: Boolean) {
        displayLoadedUI()
        if (wasSuccessful) {
            findNavController().popBackStack()
        } else {
            displayUnsuccessfulSendingSnackBar()
        }
    }

    private fun displayUnsuccessfulSendingSnackBar() {
        //TODO when the actual backend will be connected, this will be made better
        Snackbar.make(
            requireView(),
            getString(R.string.error_unsuccessful_invitation_sending),
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventInvitationSenderBinding.inflate(
            inflater,
            //R.layout.fragment_event_invitation_sender,
            container,
            false
        )
        binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        setToolbarBehaviour()
        setFabBehaviour()
        setAddButtonBehaviour()
        observeAddedInvitedUsers()
    }

    /**
     * Sets the contained toolbar's onclick behaviour. After the navigation icon was clicked, the
     * user is navigated back by popping the BackStack.
     */
    private fun setToolbarBehaviour() {
        with(binding.tbEventDetailsCommunication) {
            setNavigationOnClickListener {
                if (eventInvitationSenderViewModel.invitedUsers.value!!.isEmpty()) {
                    findNavController().popBackStack()
                } else {
                    displayClosingConfirmationDialog()
                }
            }
        }
    }

    private fun displayClosingConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Are you sure about closing the invitation sender?")
            .setPositiveButton("Close") { dialog, _ ->
                findNavController().popBackStack()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun setAddButtonBehaviour() {
        binding.btnAddInvitedUserName.setOnClickListener {
            binding.etInvitedUserNameInput.text.toString().let { addedUsername ->
                if (addedUsername != "" && !eventInvitationSenderViewModel.invitedUsers.value!!.contains(
                        addedUsername
                    )
                ) {
                    //TODO probably not the best solution
                    eventInvitationSenderViewModel.invitedUsers.value!!.add(addedUsername)
                    eventInvitationSenderViewModel.invitedUsers.value = eventInvitationSenderViewModel.invitedUsers.value!!
                }
            }
        }
    }

    private fun observeAddedInvitedUsers() {
        eventInvitationSenderViewModel.invitedUsers.observe(viewLifecycleOwner) {
            Log.d("Together!", "Szia Uram")
            setDisplayedInvitedUsers(it)
        }
    }

    private fun setDisplayedInvitedUsers(usernameList: List<String>) {
        var displayedUserNames = ""
        for ((index, username) in usernameList.withIndex()) {
            displayedUserNames += if (index == usernameList.size - 1) username else "$username, "
        }
        binding.tvSelectedUsernames.text = displayedUserNames
    }

    /**
     * Sets the contained FAB onclick behaviour. Clicking this button sends the invites to the
     * specified users, then navigates back the ser by popping the BackStack.
     */
    private fun setFabBehaviour() {
        binding.fabSendInvites.setOnClickListener {
            eventInvitationSenderViewModel.sendInvitations(args.eventId)
        }
    }

    override fun onStart() {
        super.onStart()
        eventInvitationSenderViewModel.loadEventDetails(args.eventId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}