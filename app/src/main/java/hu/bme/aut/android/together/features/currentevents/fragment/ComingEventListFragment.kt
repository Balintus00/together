package hu.bme.aut.android.together.features.currentevents.fragment

import androidx.navigation.fragment.findNavController

/**
 * This Fragment contains the list of the user's current events, that will happen in the future.
 */
class ComingEventListFragment : EventListFragment() {

    /**
     * The FAB on this Fragment can be used to navigate to the event adding screen, to an
     * [hu.bme.aut.android.together.features.addevent.fragment.AddEventPagerFragment] instance.
     */
    override fun setFabBehaviour() {
        binding.eventListAddFab.setOnClickListener {
            EventListsPagerFragmentDirections.actionCurrentEventsListFragmentToAddEventPagerFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }
}