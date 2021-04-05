package hu.bme.aut.android.together.features.eventdetails.fragment.communication

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentNewsListBinding
import hu.bme.aut.android.together.features.shared.eventmessage.adapter.EventMessagesAdapter
import hu.bme.aut.android.together.features.eventdetails.dialogfragment.EventPostNewsDialogFragment
import hu.bme.aut.android.together.model.presentation.EventMessage

/**
 * This fragment displays a list of news about the event.
 * Use [createFragment] factory method to instantiate this class.
 * This factory method is temporary to the visual demo version, and will be removed later.
 */
class NewsListFragment : Fragment() {

    companion object {
        //TODO this will be removed later
        private const val IS_ORGANISER_DATA_KEY = "IS_ORGANISER_DATA_KEY"
        fun createFragment(isOrganiser: Boolean): NewsListFragment {
            return NewsListFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_ORGANISER_DATA_KEY, isOrganiser)
                }
            }
        }
    }

    private lateinit var binding: FragmentNewsListBinding

    private lateinit var adapter: EventMessagesAdapter

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
     * the adapter set to an [EventMessagesAdapter] instance. In the constructor of the adapter
     * the list item onclick behaviour is passed; when an items is clicked, [showNewsInformationInDialog]
     * method is fired using the data provided by the adapter.
     */
    private fun setUpRecyclerView() {
        val context = requireContext()
        adapter = EventMessagesAdapter { representedNews ->
            showNewsInformationInDialog(representedNews)
        }
        binding.rvEventNews.adapter = adapter
        binding.rvEventNews.layoutManager = LinearLayoutManager(context)
    }

    /**
     * Displays a dialog to the user, that contains the data of the given [EventMessage]
     * instance.
     * @param eventMessage the data holder instance, which is used to set the content
     * of the dialog.
     */
    private fun showNewsInformationInDialog(eventMessage: EventMessage) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(eventMessage.title)
            setMessage(eventMessage.message)
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
        if (gatherIsOrganiserFromArguments()) {
            with(binding.fabOrganiserCreatePost) {
                visibility = View.VISIBLE
                setOnClickListener {
                    EventPostNewsDialogFragment().show(parentFragmentManager, "")
                }
            }
        }
    }

    private fun gatherIsOrganiserFromArguments(): Boolean {
        return arguments?.getBoolean(IS_ORGANISER_DATA_KEY) ?: false
    }

}