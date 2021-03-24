package hu.bme.aut.android.together.features.currentevents.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.databinding.FragmentCurrentEventListBinding
import hu.bme.aut.android.together.features.shared.eventlist.adapter.EventListAdapter

abstract class CurrentEventListFragment : Fragment() {

    //TODO this data mocking will be removed later
    companion object {
        private val eventDetailsItemOptionsArray = arrayOf(
            arrayOf(false, true, false, true),
            arrayOf(false, false, true, false),
            arrayOf(true, false, true, true)
        )
    }

    protected lateinit var binding: FragmentCurrentEventListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFabBehaviour()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.rvEvents) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = EventListAdapter { position ->
                eventDetailsItemOptionsArray[position].let { optionsArray ->
                    CurrentEventsListsContainerFragmentDirections.actionCurrentEventsListFragmentToEventDetailsFragment(
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

    protected abstract fun setFabBehaviour()
}