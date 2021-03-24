package hu.bme.aut.android.together.features.currentevents.fragment

import androidx.navigation.fragment.findNavController

class ComingEventListFragment : CurrentEventListFragment() {
    override fun setFabBehaviour() {
        binding.eventListAddFab.setOnClickListener {
            CurrentEventsListsContainerFragmentDirections.actionCurrentEventsListFragmentToAddEventPagerFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }
}