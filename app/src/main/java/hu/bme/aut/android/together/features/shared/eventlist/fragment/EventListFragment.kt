package hu.bme.aut.android.together.features.shared.eventlist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.databinding.FragmentEventListBinding
import hu.bme.aut.android.together.features.shared.eventlist.adapter.EventListAdapter

class EventListFragment : Fragment() {

    companion object {
        private const val ACTION_ARGUMENT_KEY = "ACTION_ARGUMENT_KEY"
        private const val ON_ITEM_CLICK_NAVIGATION_DISABLED_DEFAULT_VALUE = -1

        fun createEventListFragment(
            itemNavigationActionId: Int = ON_ITEM_CLICK_NAVIGATION_DISABLED_DEFAULT_VALUE
        ): EventListFragment {
            return EventListFragment().apply {
                arguments = Bundle().apply{
                    putInt(ACTION_ARGUMENT_KEY, itemNavigationActionId)
                }
            }
        }
    }

    private lateinit var binding: FragmentEventListBinding

    private lateinit var adapter: EventListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeRecyclerViewAdapter()
    }

    private fun initializeRecyclerViewAdapter() {
        val actionId = arguments?.getInt(ACTION_ARGUMENT_KEY)
            ?: throw IllegalStateException("Arguments was null!")
        adapter =
            if (actionId != ON_ITEM_CLICK_NAVIGATION_DISABLED_DEFAULT_VALUE)
                EventListAdapter { findNavController().navigate(actionId) }
            else
                EventListAdapter {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvEventList.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = this@EventListFragment.adapter
        }
    }

}