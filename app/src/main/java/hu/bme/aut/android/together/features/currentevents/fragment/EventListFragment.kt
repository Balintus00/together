package hu.bme.aut.android.together.features.currentevents.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.databinding.FragmentEventListBinding
import hu.bme.aut.android.together.features.shared.eventlist.adapter.EventListAdapter

/**
 * This Fragment contains a list of events, and a FAB, which represents the Fragment's main action.
 */
abstract class EventListFragment : Fragment() {

    //TODO this data mocking will be removed later
    companion object {
        private val eventDetailsItemOptionsArray = arrayOf(
            arrayOf(false, true, false, true),
            arrayOf(false, false, true, false),
            arrayOf(true, false, true, true)
        )
    }

    protected lateinit var binding: FragmentEventListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFabBehaviour()
        initRecyclerView()
    }

    /**
     * Initializes the contained RecyclerView widget's adapter and layoutManager.
     * [LinearLayoutManager] is used as its layoutManager, and the adapter is set to an [EventListAdapter]
     * instance. When an item event item is clicked, the user should be navigated to a
     * [hu.bme.aut.android.together.features.eventdetails.fragment.details.EventDetailsFragment]
     * instance. This behaviour is passed as a method reference in the adapter's constructor.
     */
    private fun initRecyclerView() {
        with(binding.rvEvents) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = EventListAdapter { position ->
                eventDetailsItemOptionsArray[position].let { optionsArray ->
                    EventListsPagerFragmentDirections.actionCurrentEventsListFragmentToEventDetailsFragment(
                        isOrganiser = optionsArray[0],
                        isPrivate = optionsArray[1],
                        isParticipantCountLimited = optionsArray[2],
                        isParticipant = optionsArray[3]
                    )
                        .let { action ->
                            findNavController().navigate(action)
                        }
                }
            }
        }
    }

    /**
     * Sets the behaviour of the contained FAB widget.
     */
    protected abstract fun setFabBehaviour()
}