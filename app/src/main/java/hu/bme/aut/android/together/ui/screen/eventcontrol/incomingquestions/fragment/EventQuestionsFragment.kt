package hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.fragment

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
import hu.bme.aut.android.together.databinding.FragmentEventQuestionsBinding
import hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.dialogfragment.EventQuestionResponseDialogFragment
import hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.adapter.EventQuestionsAdapter
import hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.viewmodel.EventQuestionsLoaded
import hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.viewmodel.EventQuestionsState
import hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.viewmodel.EventQuestionsViewModel
import hu.bme.aut.android.together.ui.screen.eventcontrol.incomingquestions.viewmodel.Loading
import hu.bme.aut.android.together.ui.screen.incomiginvitations.adapter.EventInvitationsAdapter
import hu.bme.aut.android.together.ui.model.EventQuestion
import hu.bme.aut.android.together.ui.model.EventQuestionsWithTitle

/**
 * This fragment displays a list of questions. This is currently used to display the question inbox
 * of the organisers of the event.
 */
@AndroidEntryPoint
class EventQuestionsFragment : RainbowCakeFragment<EventQuestionsState, EventQuestionsViewModel>() {

    private val args: EventQuestionsFragmentArgs by navArgs()

    private val eventQuestionsViewModel: EventQuestionsViewModel by viewModels()

    private lateinit var binding: FragmentEventQuestionsBinding

    private lateinit var adapter: EventQuestionsAdapter

    override fun provideViewModel(): EventQuestionsViewModel = eventQuestionsViewModel

    override fun render(viewState: EventQuestionsState) {
        when (viewState) {
            is Loading -> {
                displayLoadingUI()
            }
            is EventQuestionsLoaded -> {
                displayLoadedData(viewState.questionsWithTitle)
            }
        }.exhaustive
    }

    private fun displayLoadingUI() {
        binding.clContent.isVisible = false
        binding.cpiQuestionsLoading.isVisible = true
    }

    private fun displayLoadedData(data: EventQuestionsWithTitle) {
        binding.tbEventDetailsQuestions.title = data.title
        adapter.submitList(data.questions)
        binding.cpiQuestionsLoading.isVisible = false
        binding.clContent.isVisible = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        setUpToolbarBehaviour()
        setUpRecyclerView()
    }

    /**
     * Sets toolbar's navigation icon to pop the BackStack when it is clicked.
     */
    private fun setUpToolbarBehaviour() {
        with(binding.tbEventDetailsQuestions) {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    /**
     * Sets up the fragment's RecyclerView, which contains the list of questions.
     * As a layout manager a [LinearLayoutManager], and as an adapter an [EventInvitationsAdapter]
     * instance is set. In the adapter's constructor the items' onclick behaviour is passed.
     * When an item is clicked, a dialog will be displayed, on which the user can answer the
     * chosen question; this behaviour is implemented by [showEventQuestionResponseDialog] method.
     */
    private fun setUpRecyclerView() {
        adapter = EventQuestionsAdapter { representedQuestion ->
            showEventQuestionResponseDialog(representedQuestion)
        }
        with(binding.rvQuestions) {
            adapter = this@EventQuestionsFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    /**
     * Pops up an AlertDialog, that can be used to answer a question.
     * @param eventQuestion the instance, which contains the data of the question.
     */
    private fun showEventQuestionResponseDialog(eventQuestion: EventQuestion) {
        EventQuestionResponseDialogFragment.newInstance(eventQuestion)
            .show(parentFragmentManager, "")
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadEventQuestions(args.eventId)
    }
}