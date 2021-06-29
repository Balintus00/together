package hu.bme.aut.android.together.ui.screen.eventcontrol.communication.newslist.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentNewsListBinding
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.newslist.adapter.EventNewsAdapter
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.newslist.viewmodel.Loading
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.newslist.viewmodel.NewsListLoaded
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.newslist.viewmodel.NewsListState
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.newslist.viewmodel.NewsListViewModel
import hu.bme.aut.android.together.ui.screen.eventcontrol.communication.newslist.dialogfragment.EventPostNewsDialogFragment
import hu.bme.aut.android.together.ui.screen.incomiginvitations.adapter.EventInvitationsAdapter
import hu.bme.aut.android.together.ui.model.EventInvitation
import hu.bme.aut.android.together.ui.model.EventNews

/**
 * This fragment displays a list of news about the event.
 * Use [createFragment] factory method to instantiate this class.
 * This factory method is temporary to the visual demo version, and will be removed later.
 */
@AndroidEntryPoint
class NewsListFragment : RainbowCakeFragment<NewsListState, NewsListViewModel>() {

    companion object {
        private const val EVENT_ID_DATA_KEY = "EVENT_ID_DATA_KEY"

        fun createFragment(eventId: Long): NewsListFragment {
            return NewsListFragment().apply {
                arguments = Bundle().apply {
                    putLong(EVENT_ID_DATA_KEY, eventId)
                }
            }
        }
    }

    private lateinit var binding: FragmentNewsListBinding

    private lateinit var adapter: EventNewsAdapter

    private val newsListViewModel: NewsListViewModel by viewModels()

    override fun provideViewModel(): NewsListViewModel = newsListViewModel

    override fun render(viewState: NewsListState) {
        when (viewState) {
            is Loading -> {
                displayLoadingUI()
            }
            is NewsListLoaded -> {
                displayLoadedNewsList(viewState.newsList)
            }
        }.exhaustive
    }

    private fun displayLoadingUI() {
        binding.fabOrganiserCreatePost.isVisible = false
        binding.rvEventNews.isVisible = false
        binding.cpiNewsListLoading.isVisible = true
    }

    private fun displayLoadedNewsList(loadedNewsList: List<EventNews>) {
        adapter.submitList(loadedNewsList)
        showOrganiserButtonIfAuthorized()
        binding.cpiNewsListLoading.isVisible = false
        binding.rvEventNews.isVisible = true
    }

    private fun showOrganiserButtonIfAuthorized() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setFABBehaviour()
    }

    /**
     * Sets up the fragment's RecyclerView. The layout manager is set to a [LinearLayoutManager],
     * the adapter set to an [EventInvitationsAdapter] instance. In the constructor of the adapter
     * the list item onclick behaviour is passed; when an items is clicked, [showNewsInformationInDialog]
     * method is fired using the data provided by the adapter.
     */
    private fun setUpRecyclerView() {
        val context = requireContext()
        adapter = EventNewsAdapter { representedNews ->
            showNewsInformationInDialog(representedNews)
        }
        binding.rvEventNews.adapter = adapter
        binding.rvEventNews.layoutManager = LinearLayoutManager(context)
    }

    /**
     * Displays a dialog to the user, that contains the data of the given [EventInvitation]
     * instance.
     * @param eventNews the data holder instance, which is used to set the content
     * of the dialog.
     */
    private fun showNewsInformationInDialog(eventNews: EventNews) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(eventNews.title)
            setMessage(eventNews.message)
            setPositiveButton(getString(R.string.action_back)) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
        }.show()
    }

    /**
     * Sets the fragment's FAB's behaviour. This FAB is displayed only if the user has organiser
     * privilege. The organisers can use this button to post a new news to the news feed using
     * a DialogFragment.
     */
    private fun setFABBehaviour() {
        with(binding.fabOrganiserCreatePost) {
            setOnClickListener {
                EventPostNewsDialogFragment().show(parentFragmentManager, "")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        newsListViewModel.loadEventNews(retrieveEventId())
    }

    private fun retrieveEventId(): Long {
        return requireArguments().getLong(EVENT_ID_DATA_KEY)
    }

}