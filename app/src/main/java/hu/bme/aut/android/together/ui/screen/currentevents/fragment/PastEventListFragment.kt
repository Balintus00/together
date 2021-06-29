package hu.bme.aut.android.together.ui.screen.currentevents.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentEventListBinding
import hu.bme.aut.android.together.ui.screen.currentevents.viewmodel.*
import hu.bme.aut.android.together.ui.screen.shared.adapter.EventListAdapter
import hu.bme.aut.android.together.ui.model.EventShortInfo

/**
 * This Fragment contains the list of the user's past events.
 */
@AndroidEntryPoint
class PastEventListFragment : RainbowCakeFragment<EventListState, PastEventListViewModel>() {

    //TODO this data mocking will be removed later
    companion object {
        private const val FAKE_PROFILE_ID = 1L
    }

    private lateinit var eventListAdapter: EventListAdapter

    private lateinit var binding: FragmentEventListBinding

    private val pastEventListViewModel: PastEventListViewModel by viewModels()

    override fun provideViewModel(): PastEventListViewModel = pastEventListViewModel

    override fun render(viewState: EventListState) {
        when (viewState) {
            is Loading -> {
                binding.cpiEventListLoading.isVisible = true
                binding.flContent.isVisible = false
            }
            is EventListLoaded -> {
                binding.cpiEventListLoading.isVisible = false
                setUpUIOnLoaded(viewState.eventShortInfoList)
                binding.flContent.isVisible = true
            }
            is LoadingError -> {
                binding.cpiEventListLoading.isVisible = false
                binding.flContent.isVisible = false
                displayError(viewState.errorMessage)
            }
        }.exhaustive
    }

    private fun setUpUIOnLoaded(eventShortInfoList: List<EventShortInfo>) {
        eventListAdapter.submitList(eventShortInfoList)
    }

    private fun displayError(errorMessage: String) {
        Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_LONG).setAction(
            getString(R.string.action_reload)
        ) { viewModel.loadPastEventShortInfoListByProfileId(FAKE_PROFILE_ID) }
            .show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFabBehaviour()
        initRecyclerView()
    }

    /**
     * Initializes the contained RecyclerView widget's adapter and layoutManager.
     * [LinearLayoutManager] is used as its layoutManager, and the adapter is set to an [EventListAdapter]
     * instance. When an item event item is clicked, the user should be navigated to a
     * [hu.bme.aut.android.together.features.eventcontrol.details.fragment.EventDetailsFragment]
     * instance. This behaviour is passed as a method reference in the adapter's constructor.
     */
    private fun initRecyclerView() {
        with(binding.rvEvents) {
            layoutManager = LinearLayoutManager(requireContext())
            eventListAdapter = EventListAdapter { position ->
                EventListsPagerFragmentDirections.actionCurrentEventsListFragmentToEventDetailsFragment(
                    (position + 1).toLong()
                )
                    .let { action ->
                        findNavController().navigate(action)
                    }
            }
            adapter = eventListAdapter
        }
    }

    /**
     * This Fragment doesn't use the provided FAB, so it is hidden.
     */
    private fun setFabBehaviour() {
        binding.eventListAddFab.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadPastEventShortInfoListByProfileId(FAKE_PROFILE_ID)
    }
}