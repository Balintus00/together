package hu.bme.aut.android.together.features.currentevents.fragment

import kotlinx.android.synthetic.main.fragment_current_event_list.*

class ComingEventListFragment : CurrentEventListFragment() {
    override fun setFabBehaviour() {
        eventListAddFab.setOnClickListener{
            // TODO navigating to event creation view
        }
    }
}