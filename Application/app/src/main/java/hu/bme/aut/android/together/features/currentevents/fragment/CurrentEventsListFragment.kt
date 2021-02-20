package hu.bme.aut.android.together.features.currentevents.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import hu.bme.aut.android.together.databinding.FragmentCurrentEventsListBinding
import hu.bme.aut.android.together.features.currentevents.adapter.EventTimeAdapter
import java.lang.IllegalStateException

class CurrentEventsListFragment : Fragment() {

    private lateinit var binding: FragmentCurrentEventsListBinding
    private lateinit var pagerAdapter: EventTimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentEventsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEventTimePagerAdapter()
        attachEventTimeTabLayoutMediator()
    }

    private fun setEventTimePagerAdapter(){
        pagerAdapter = EventTimeAdapter(this)
        binding.eventTimePager.adapter = pagerAdapter
    }

    private fun attachEventTimeTabLayoutMediator(){
        TabLayoutMediator(binding.eventTimeTabLayout, binding.eventTimePager){ tab, position ->
            tab.text = pagerAdapter.getTabName(position)
        }.attach()
    }
}