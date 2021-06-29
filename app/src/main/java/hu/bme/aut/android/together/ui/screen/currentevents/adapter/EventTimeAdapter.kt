package hu.bme.aut.android.together.ui.screen.currentevents.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.ui.screen.currentevents.fragment.ComingEventListFragment
import hu.bme.aut.android.together.ui.screen.currentevents.fragment.PastEventListFragment

/**
 * This adapter is used to control an [hu.bme.aut.android.together.features.currentevents.fragment.EventListsPagerFragment]
 * instance's ViewPager2 widget.
 */
class EventTimeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    companion object {
        private const val TAB_COUNT = 2
    }

    private val context = fragment.requireContext()

    override fun getItemCount(): Int {
        return TAB_COUNT
    }

    /**
     * The adapter provides the following fragments:
     * - 0. : [ComingEventListFragment]
     * - 1. : [PastEventListFragment]
     */
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ComingEventListFragment()
            1 -> PastEventListFragment()
            else -> throw IllegalStateException("In EventTimeAdapter position is greater than 1!")
        }
    }

    /**
     * Returns the tab name, that fits the fragment at the given index.
     * @param index the position of the Tab in the list.
     */
    fun getTabName(index: Int): String {
        return when (index) {
            0 -> context.getString(R.string.tab_name_coming_current_events)
            1 -> context.getString(R.string.tab_name_past_current_events)
            else -> throw IllegalArgumentException("EventTimeAdapter.getTabName illegal index argument was given: $index")
        }
    }
}