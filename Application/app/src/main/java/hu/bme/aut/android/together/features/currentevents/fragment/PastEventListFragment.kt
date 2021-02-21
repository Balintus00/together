package hu.bme.aut.android.together.features.currentevents.fragment

import android.view.View

class PastEventListFragment : CurrentEventListFragment() {
    override fun setFabBehaviour() {
        binding.eventListAddFab.visibility = View.GONE
    }
}