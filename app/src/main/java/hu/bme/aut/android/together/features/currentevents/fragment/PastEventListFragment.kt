package hu.bme.aut.android.together.features.currentevents.fragment

import android.view.View

/**
 * This Fragment contains the list of the user's past events.
 */
class PastEventListFragment : EventListFragment() {

    /**
     * This Fragment doesn't use the provided FAB, so it is hidden.
     */
    override fun setFabBehaviour() {
        binding.eventListAddFab.visibility = View.GONE
    }
}