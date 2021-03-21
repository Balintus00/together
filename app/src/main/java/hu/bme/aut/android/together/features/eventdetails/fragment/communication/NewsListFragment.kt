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
import hu.bme.aut.android.together.features.eventdetails.adapter.EventMessagesAdapter
import hu.bme.aut.android.together.features.eventdetails.dialogfragment.EventPostNewsDialogFragment
import hu.bme.aut.android.together.model.EventNewsMessage
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsListFragment : Fragment() {

    companion object {
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

    private fun setUpRecyclerView() {
        val context = requireContext()
        adapter = EventMessagesAdapter { representedNews ->
            showNewsInformationInDialog(representedNews)
        }
        rvEventNews.adapter = adapter
        rvEventNews.layoutManager = LinearLayoutManager(context)
    }

    private fun showNewsInformationInDialog(eventNewsMessage: EventNewsMessage) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(eventNewsMessage.title)
            setMessage(eventNewsMessage.message)
            setPositiveButton(getString(R.string.action_back)) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
        }.show()
    }

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