package hu.bme.aut.android.together.features.currentevents.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import hu.bme.aut.android.together.features.currentevents.fragment.ComingEventListFragment
import hu.bme.aut.android.together.features.currentevents.fragment.PastEventListFragment

class EventTimeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ComingEventListFragment()
            1 -> PastEventListFragment()
            else -> throw IllegalStateException("In EventTimeAdapter position is greater than 1!")
        }
    }

    fun getTabName(index: Int): String {
        return when (index) {
            0 -> "Coming"
            1 -> "Past"
            else -> throw IllegalArgumentException("EventTimeAdapter.getTabName illegal index argument was given: $index")
        }
    }
}