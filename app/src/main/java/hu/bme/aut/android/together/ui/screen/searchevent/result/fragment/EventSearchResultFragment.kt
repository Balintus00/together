package hu.bme.aut.android.together.ui.screen.searchevent.result.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.databinding.FragmentEventSearchResultBinding
import hu.bme.aut.android.together.ui.screen.searchevent.result.viewmodel.EventSearchResultState
import hu.bme.aut.android.together.ui.screen.searchevent.result.viewmodel.EventSearchResultViewModel
import hu.bme.aut.android.together.ui.screen.searchevent.result.viewmodel.Loading
import hu.bme.aut.android.together.ui.screen.searchevent.result.viewmodel.ResultsLoaded
import hu.bme.aut.android.together.ui.screen.shared.adapter.EventListAdapter
import hu.bme.aut.android.together.ui.model.EventQueryParameter
import hu.bme.aut.android.together.ui.model.EventShortInfo

/**
 * This Fragment displays the result of the search of events, using the specified search filters in
 * the instance's navigation arguments.
 */
@AndroidEntryPoint
class EventSearchResultFragment :
    RainbowCakeFragment<EventSearchResultState, EventSearchResultViewModel>() {

    private val args: EventSearchResultFragmentArgs by navArgs()

    private lateinit var binding: FragmentEventSearchResultBinding

    private val eventSearchResultViewModel: EventSearchResultViewModel by viewModels()

    private lateinit var listAdapter: EventListAdapter

    override fun provideViewModel(): EventSearchResultViewModel = eventSearchResultViewModel

    override fun render(viewState: EventSearchResultState) {
        when (viewState) {
            is Loading -> {
                binding.rvEventResults.isVisible = false
                binding.cpiEventSearchResult.isVisible = true
            }
            is ResultsLoaded -> {
                loadDataOnLoadedState(viewState.results)
                binding.cpiEventSearchResult.isVisible = false
                binding.rvEventResults.isVisible = true
            }
        }.exhaustive
    }

    private fun loadDataOnLoadedState(results: List<EventShortInfo>) {
        listAdapter.submitList(results)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpRecyclerView()
    }

    /**
     * This function sets the fragment's toolbar (of which id is tbSearchResults) navigation click
     * behaviour. Simply when the toolbar's navigation icon is clicked, the BackStack is popped.
     */
    private fun setUpToolbar() {
        with(binding.tbSearchResults) {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    /**
     * This function sets the contained RecyclerView instance's (of which id is rvEventResults)
     * layoutManager as a [LinearLayoutManager] instance, and the adapter as an [EventListAdapter]
     * instance. In the adapter's constructor the item on click behaviour is passed. When a
     * contained item of this RecyclerView is clicked, it should navigate to an
     * [hu.bme.aut.android.together.features.eventcontrol.details.fragment.EventDetailsFragment]
     * instance, which displays the clicked event item's information.
     */
    private fun setUpRecyclerView() {
        listAdapter = EventListAdapter { position ->
            EventSearchResultFragmentDirections.actionEventSearchResultFragmentToEventDetailsFragment(
                (position + 1).toLong()
            ).let { action ->
                binding.rvEventResults.findNavController().navigate(action)
            }
        }
        with(binding.rvEventResults) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadResults(getQueryParameterFromNavArgs())
    }

    private fun getQueryParameterFromNavArgs(): EventQueryParameter {
        return args.queryParameter
    }
}