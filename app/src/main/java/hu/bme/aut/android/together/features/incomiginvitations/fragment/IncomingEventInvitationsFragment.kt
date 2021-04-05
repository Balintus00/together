package hu.bme.aut.android.together.features.incomiginvitations.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.extensions.exhaustive
import hu.bme.aut.android.together.databinding.FragmentIncomingEventInvitationsBinding
import hu.bme.aut.android.together.features.shared.eventmessage.adapter.EventMessagesAdapter
import hu.bme.aut.android.together.features.incomiginvitations.dialogfragment.InvitationResponderDialogFragment
import hu.bme.aut.android.together.features.incomiginvitations.viewmodel.IncomingEventInvitationsLoaded
import hu.bme.aut.android.together.features.incomiginvitations.viewmodel.IncomingEventInvitationsState
import hu.bme.aut.android.together.features.incomiginvitations.viewmodel.IncomingEventInvitationsViewModel
import hu.bme.aut.android.together.features.incomiginvitations.viewmodel.Loading
import hu.bme.aut.android.together.model.presentation.EventMessage

/**
 * This Fragment displays the user's incoming invitations. The user can accept or decline these
 * invites here using [InvitationResponderDialogFragment].
 */
class IncomingEventInvitationsFragment :
    RainbowCakeFragment<IncomingEventInvitationsState, IncomingEventInvitationsViewModel>() {

    companion object {
        private const val MOCKED_PROFILE_ID = 1L
    }

    private lateinit var binding: FragmentIncomingEventInvitationsBinding

    private lateinit var adapter: EventMessagesAdapter

    override fun provideViewModel(): IncomingEventInvitationsViewModel = getViewModelFromFactory()

    override fun render(viewState: IncomingEventInvitationsState) {
        when (viewState) {
            is Loading -> {
                binding.cpiInvitesInboxLoading.isVisible = true
                binding.rvIncomingInvites.isVisible = false
            }
            is IncomingEventInvitationsLoaded -> {
                binding.cpiInvitesInboxLoading.isVisible = false
                setUpUIOnLoaded(viewState.invites)
                binding.rvIncomingInvites.isVisible = true
            }
        }.exhaustive
    }

    private fun setUpUIOnLoaded(eventInvitations: List<EventMessage>) {
        adapter.submitList(eventInvitations)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIncomingEventInvitationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUiWidgets()
    }

    private fun setUpUiWidgets() {
        setToolbarNavigationBehaviour()
        setUpRecyclerView()
    }

    /**
     * This function sets the toolbar's (with the id of tbInvitations) navigation behaviour, to pop
     * the BackStack when the toolbar's navigation action is clicked.
     */
    private fun setToolbarNavigationBehaviour() {
        with(binding.tbInvitations) {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    /**
     * This function sets the adapter and the layoutManager of the contained recyclerView
     * (with the id of rvIncomingInvites). The layoutManager uses the built in
     * [LinearLayoutManager]'s instance, and the adapter is set to be a [EventMessagesAdapter]
     * instance.
     */
    private fun setUpRecyclerView() {
        adapter = EventMessagesAdapter {
            InvitationResponderDialogFragment(it).show(parentFragmentManager, "")
        }
        with(binding.rvIncomingInvites) {
            adapter = this@IncomingEventInvitationsFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadIncomingInvitesByProfileId(MOCKED_PROFILE_ID)
    }
}