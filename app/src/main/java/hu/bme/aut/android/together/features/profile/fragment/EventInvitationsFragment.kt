package hu.bme.aut.android.together.features.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.databinding.FragmentEventInvitationsBinding
import hu.bme.aut.android.together.features.shared.eventmessage.adapter.EventMessagesAdapter
import hu.bme.aut.android.together.features.profile.dialogfragment.InvitationResponderDialogFragment

/**
 * This Fragment displays the user's incoming invitations. The user can accept or decline these
 * invites here using [InvitationResponderDialogFragment].
 */
class EventInvitationsFragment : Fragment() {

    private lateinit var binding: FragmentEventInvitationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventInvitationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
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
        with(binding.rvIncomingInvites) {
            adapter = EventMessagesAdapter{
                InvitationResponderDialogFragment(it).show(parentFragmentManager, "")
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}