package hu.bme.aut.android.together.features.currentevents.fragment

import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_current_event_list.*

class ComingEventListFragment : CurrentEventListFragment() {
    override fun setFabBehaviour() {
        eventListAddFab.setOnClickListener {
            CurrentEventsListsContainerFragmentDirections.actionCurrentEventsListFragmentToNameAdderFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }
}