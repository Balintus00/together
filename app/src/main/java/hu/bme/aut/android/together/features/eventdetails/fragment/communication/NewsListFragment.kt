package hu.bme.aut.android.together.features.eventdetails.fragment.communication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.databinding.FragmentNewsListBinding
import hu.bme.aut.android.together.features.eventdetails.adapter.EventNewsAdapter
import hu.bme.aut.android.together.features.eventdetails.dialogfragment.EventPostNewsDialogFragment
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsListFragment : Fragment() {

    companion object{
        private const val IS_ORGANISER_DATA_KEY = "IS_ORGANISER_DATA_KEY"
        fun createFragment(isOrganiser: Boolean) : NewsListFragment{
            return NewsListFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_ORGANISER_DATA_KEY, isOrganiser)
                }
            }
        }
    }

    private lateinit var binding: FragmentNewsListBinding

    private lateinit var adapter: EventNewsAdapter

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
        adapter = EventNewsAdapter(context)
        rvEventNews.adapter = adapter
        rvEventNews.layoutManager = LinearLayoutManager(context)
    }

    private fun setFABBehaviour() {
        if(gatherIsOrganiserFromArguments()) {
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