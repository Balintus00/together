package hu.bme.aut.android.together.features.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.databinding.FragmentEventInvitationsBinding
import hu.bme.aut.android.together.features.eventdetails.adapter.EventMessagesAdapter
import hu.bme.aut.android.together.features.profile.dialogfragment.InvitationResponderDialogFragment

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
        setUpToolbar()
        setUpRecyclerView()
    }

    private fun setUpToolbar() {
        with(binding.tbInvitations) {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setUpRecyclerView() {
        with(binding.rvIncomingInvites) {
            adapter = EventMessagesAdapter{
                InvitationResponderDialogFragment(it).show(parentFragmentManager, "")
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}