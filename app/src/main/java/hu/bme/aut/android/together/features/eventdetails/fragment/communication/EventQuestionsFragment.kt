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
import hu.bme.aut.android.together.model.EventNewsMessage

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

    private fun setUpToolbarBehaviour() {
        with(binding.tbEventDetailsQuestions) {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setUpRecyclerView() {
        with(binding.rvQuestions) {
            adapter = EventMessagesAdapter{ representedNews ->
                showEventQuestionResponseDialog(representedNews)
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun showEventQuestionResponseDialog(eventNewsMessage: EventNewsMessage) {
        EventQuestionResponseDialogFragment(eventNewsMessage).show(parentFragmentManager, "")
    }
}