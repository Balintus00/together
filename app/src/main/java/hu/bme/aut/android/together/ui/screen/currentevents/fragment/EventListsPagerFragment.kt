package hu.bme.aut.android.together.ui.screen.currentevents.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import hu.bme.aut.android.together.databinding.FragmentEventListsPagerBinding
import hu.bme.aut.android.together.ui.screen.currentevents.adapter.EventTimeAdapter

/**
 * This fragment contains the lists of events, which the user participated in.
 * The lists are separated by a ViewPager2 widget.
 */
class EventListsPagerFragment : Fragment() {

    private var _binding: FragmentEventListsPagerBinding? = null

    private val binding get() = _binding!!

    private lateinit var pagerAdapter: EventTimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventListsPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEventTimePagerAdapter()
        attachEventTimeTabLayoutMediator()
    }

    /**
     * Sets the ViewPager2 widget's adapter to a [EventTimeAdapter] instance.
     */
    private fun setEventTimePagerAdapter() {
        pagerAdapter = EventTimeAdapter(this)
        binding.eventTimePager.adapter = pagerAdapter
    }

    /**
     * Attaches a [TabLayoutMediator] to the ViewPager2 widget. The tabs' text are set by using
     * [EventTimeAdapter.getTabName].
     */
    private fun attachEventTimeTabLayoutMediator() {
        TabLayoutMediator(binding.eventTimeTabLayout, binding.eventTimePager) { tab, position ->
            tab.text = pagerAdapter.getTabName(position)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}