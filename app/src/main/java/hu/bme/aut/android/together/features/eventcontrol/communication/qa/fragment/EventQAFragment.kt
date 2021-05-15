package hu.bme.aut.android.together.features.eventcontrol.communication.qa.fragment

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
import hu.bme.aut.android.together.databinding.FragmentEventQABinding
import hu.bme.aut.android.together.features.eventcontrol.communication.qa.adapter.EventQAAdapter
import hu.bme.aut.android.together.features.eventcontrol.communication.qa.dialogfragment.EventQuestionAskingDialogFragment
import hu.bme.aut.android.together.features.eventcontrol.communication.qa.fragment.EventQAFragment.Companion.createFragment
import hu.bme.aut.android.together.features.eventcontrol.communication.qa.viewmodel.EventQALoaded
import hu.bme.aut.android.together.features.eventcontrol.communication.qa.viewmodel.EventQAState
import hu.bme.aut.android.together.features.eventcontrol.communication.qa.viewmodel.EventQAViewModel
import hu.bme.aut.android.together.features.eventcontrol.communication.qa.viewmodel.Loading
import hu.bme.aut.android.together.model.presentation.EventAnswer
import hu.bme.aut.android.together.model.presentation.EventQuestion
import hu.bme.aut.android.together.model.presentation.EventQuestionAndAnswer

/**
 * This Fragment contains the QA panel for the events.
 * Use [createFragment] factory method to instantiate this fragment.
 */
@AndroidEntryPoint
class EventQAFragment : RainbowCakeFragment<EventQAState, EventQAViewModel>() {

    companion object {
        private const val EVENT_ID_KEY = "EVENT_ID_KEY"
        fun createFragment(eventId: Long): EventQAFragment {
            return EventQAFragment().apply {
                arguments = Bundle().apply {
                    putLong(EVENT_ID_KEY, eventId)
                }
            }
        }
    }

    private lateinit var binding: FragmentEventQABinding

    private lateinit var adapter: EventQAAdapter

    private val eventQAViewModel: EventQAViewModel by viewModels()

    override fun provideViewModel(): EventQAViewModel = eventQAViewModel

    override fun render(viewState: EventQAState) {
        when(viewState) {
            is Loading -> {
                displayLoadingScreen()
            }
            is EventQALoaded -> {
                displayLoadedQuestionAndAnswers(viewState.qaList)
            }
        }.exhaustive
    }

    private fun displayLoadingScreen() {
        with(binding) {
            rvQuestionsAndAnswers.isVisible = false
            fabAskQuestion.isVisible = false
            cpiQALoading.isVisible = true
        }
    }

    private fun displayLoadedQuestionAndAnswers(questionAndAnswers: List<EventQuestionAndAnswer>) {
        with(binding) {
            adapter.submitList(questionAndAnswers)
            cpiQALoading.isVisible = false
            fabAskQuestion.isVisible = true
            rvQuestionsAndAnswers.isVisible = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventQABinding.inflate(inflater, container, false)
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
        adapter = EventQAAdapter(::showQuestionInDialog, ::showAnswerInDialog)
        binding.rvQuestionsAndAnswers.adapter = adapter
        binding.rvQuestionsAndAnswers.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showQuestionInDialog(question: EventQuestion) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(question.question)
            setMessage(question.detailedQuestion)
            setPositiveButton(getString(R.string.action_back)) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
        }.show()
    }

    private fun showAnswerInDialog(answer: EventAnswer) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(answer.answer)
            setMessage(answer.detailedAnswer)
            setPositiveButton(getString(R.string.action_back)) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
        }.show()
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

    override fun onStart() {
        super.onStart()
        eventQAViewModel.loadQuestionsAndAnswersByEventId(requireArguments().getLong(EVENT_ID_KEY))
    }
}