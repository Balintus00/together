package hu.bme.aut.android.together.features.event.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.features.event.fragment.communication.NewsListFragment
import hu.bme.aut.android.together.features.event.fragment.communication.QAFragment

/**
 * This adapter controls the appearance of the tabs, that can be used to communicate with the other
 * event participants.
 * @param fragment the reference of the Fragment, which contains the widget, that uses this adapter.
 * @param isOrganiser if the user has organiser privileges, this value should be true.
 */
class CommunicationPanelsAdapter(fragment: Fragment, private val isOrganiser: Boolean) :
    FragmentStateAdapter(fragment) {
    companion object {
        private const val TAB_COUNT = 2
    }

    private val context = fragment.requireContext()

    override fun getItemCount(): Int {
        return TAB_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        require(position in 0 until TAB_COUNT)
        return when (position) {
            0 -> NewsListFragment.createFragment(isOrganiser)
            1 -> QAFragment()
            else -> throw IllegalArgumentException("Invalid position was given to adapter!")
        }
    }

    /**
     * Returns the name of chosen fragment's tab's name.
     * @param position the position of the fragment, which tab's name is needed.
     * @return the name of the tab, that is marked by the position paramter.
     */
    fun getTabName(position: Int): String {
        require(position in 0 until TAB_COUNT)
        return when (position) {
            0 -> context.getString(R.string.tab_name_news_event_details)
            1 -> context.getString(R.string.tab_name_qa_event_details)
            else -> throw IllegalArgumentException("Invalid position was given to adapter!")
        }
    }
}