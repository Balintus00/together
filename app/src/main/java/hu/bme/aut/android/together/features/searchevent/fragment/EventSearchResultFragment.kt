package hu.bme.aut.android.together.features.searchevent.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.databinding.FragmentEventSearchResultBinding
import hu.bme.aut.android.together.features.shared.eventlist.adapter.EventListAdapter

/**
 * This Fragment displays the result of the search of events, using the specified search filters in
 * the instance's navigation arguments.
 */
class EventSearchResultFragment : Fragment() {

    //TODO this data mocking will be removed later
    companion object {
        private val eventDetailsItemOptionsArray = arrayOf(
            arrayOf(false, true, false, true),
            arrayOf(false, false, true, false),
            arrayOf(true, false, true, true)
        )
    }

    private lateinit var binding: FragmentEventSearchResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpRecyclerView()
    }

    /**
     * This function sets the fragment's toolbar (of which id is tbSearchResults) navigation click
     * behaviour. Simply when the toolbar's navigation icon is clicked, the BackStack is popped.
     */
    private fun setUpToolbar() {
        with(binding.tbSearchResults) {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    /**
     * This function sets the contained RecyclerView instance's (of which id is rvEventResults)
     * layoutManager as a [LinearLayoutManager] instance, and the adapter as an [EventListAdapter]
     * instance. In the adapter's constructor the item on click behaviour is passed. When a
     * contained item of this RecyclerView is clicked, it should navigate to an
     * [hu.bme.aut.android.together.features.eventdetails.fragment.details.EventDetailsFragment]
     * instance, which displays the clicked event item's information.
     */
    private fun setUpRecyclerView() {
        with(binding.rvEventResults) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = EventListAdapter { position ->
                eventDetailsItemOptionsArray[position].let { optionsArray ->
                    EventSearchResultFragmentDirections.actionEventSearchResultFragmentToEventDetailsFragment(
                        isOrganiser = optionsArray[0],
                        isPrivate = optionsArray[1],
                        isParticipantCountLimited = optionsArray[2],
                        isParticipant = optionsArray[3]
                    ).let { action ->
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }
}