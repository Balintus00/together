package hu.bme.aut.android.together.features.eventdetails.fragment.communication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.databinding.FragmentEventQuestionsBinding
import hu.bme.aut.android.together.features.shared.eventmessage.adapter.EventMessagesAdapter
import hu.bme.aut.android.together.features.eventdetails.dialogfragment.EventQuestionResponseDialogFragment
import hu.bme.aut.android.together.model.presentation.EventMessage

/**
 * This fragment displays a list of questions. This is currently used to display the question inbox
 * of the organisers of the event.
 */
class EventQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentEventQuestionsBinding

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
     * As a layout manager a [LinearLayoutManager], and as an adapter an [EventMessagesAdapter]
     * instance is set. In the adapter's constructor the items' onclick behaviour is passed.
     * When an item is clicked, a dialog will be displayed, on which the user can answer the
     * chosen question; this behaviour is implemented by [showEventQuestionResponseDialog] method.
     */
    private fun setUpRecyclerView() {
        with(binding.rvQuestions) {
            adapter = EventMessagesAdapter{ representedNews ->
                showEventQuestionResponseDialog(representedNews)
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    /**
     * Pops up an AlertDialog, that can be used to answer a question.
     * @param eventMessage the instance, which contains the data of the question.
     */
    private fun showEventQuestionResponseDialog(eventMessage: EventMessage) {
        EventQuestionResponseDialogFragment(eventMessage).show(parentFragmentManager, "")
    }
}