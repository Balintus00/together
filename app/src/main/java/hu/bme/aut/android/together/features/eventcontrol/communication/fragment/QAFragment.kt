package hu.bme.aut.android.together.features.eventcontrol.communication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.databinding.FragmentQABinding
import hu.bme.aut.android.together.features.eventcontrol.communication.adapter.EventQAAdapter
import hu.bme.aut.android.together.features.eventcontrol.dialogfragment.EventQuestionAskingDialogFragment

/**
 * This Fragment contains the QA panel for the events.
 */
class QAFragment : Fragment() {

    private lateinit var binding: FragmentQABinding

    private lateinit var adapter: EventQAAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        initializeRecyclerView()
        setUpQuestionAskingFAB()
    }

    /**
     * Initializes the fragment's RecyclerView widget.
     * The layoutManager is set to a [LinearLayoutManager], and the adapter to a [EventQAAdapter]
     * instance.
     */
    private fun initializeRecyclerView() {
        adapter = EventQAAdapter(requireContext())
        binding.rvQuestionsAndAnswers.adapter = adapter
        binding.rvQuestionsAndAnswers.layoutManager = LinearLayoutManager(requireContext())
    }

    /**
     * The FAB on this fragment can be used to add questions to the event.
     * After clicking this FAB an [EventQuestionAskingDialogFragment] will be displayed,
     * that can be used to ask the question.
     */
    private fun setUpQuestionAskingFAB() {
        binding.fabAskQuestion.setOnClickListener {
            EventQuestionAskingDialogFragment().show(parentFragmentManager, "")
        }
    }
}