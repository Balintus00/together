package hu.bme.aut.android.together.features.searchevent.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.databinding.FragmentEventSearchResultBinding
import hu.bme.aut.android.together.features.shared.eventlist.fragment.EventListFragment

class EventSearchResultFragment : Fragment() {

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
        addContainedEventListFragmentIntoContainer()
    }

    private fun addContainedEventListFragmentIntoContainer() {
        childFragmentManager.beginTransaction()
            .replace(
                binding.fcvResultList.id,
                EventListFragment.createEventListFragment(
                    EventSearchResultFragmentDirections
                        .actionEventSearchResultFragmentToEventDetailsFragment().actionId)
            ).commit()
    }
}