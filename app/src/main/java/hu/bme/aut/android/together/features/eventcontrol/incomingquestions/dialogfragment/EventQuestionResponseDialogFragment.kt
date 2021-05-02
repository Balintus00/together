package hu.bme.aut.android.together.features.eventcontrol.incomingquestions.dialogfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.together.databinding.DialogfragmentEventQuestionResponseBinding
import hu.bme.aut.android.together.model.presentation.EventQuestion

class EventQuestionResponseDialogFragment : DialogFragment() {

    private lateinit var representedQuestion: EventQuestion

    private lateinit var binding: DialogfragmentEventQuestionResponseBinding

    companion object {
        private const val REPRESENTED_QUESTION_KEY = "REPRESENTED_QUESTION_KEY"
        fun newInstance(representedQuestion: EventQuestion): DialogFragment {
            return EventQuestionResponseDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(REPRESENTED_QUESTION_KEY, representedQuestion)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            gatherRepresentedQuestionFromArguments()
        }
        catch (ise: IllegalStateException) {
            onInvalidArguments(ise)
        }
    }

    private fun onInvalidArguments(ise: IllegalStateException) {
        Log.e("Together!", ise.stackTrace.toString())
        dismiss()
    }

    private fun gatherRepresentedQuestionFromArguments() {
        representedQuestion = requireArguments().getParcelable(REPRESENTED_QUESTION_KEY)
            ?: throw IllegalStateException("No EventQuestion instance was found with the given key. This error might be caused by not using the factory method.")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogfragmentEventQuestionResponseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        displayRepresentedData()
        setPositiveButtonBehaviour()
        setNegativeButtonBehaviour()
    }

    private fun displayRepresentedData() {
        with(binding) {
            tvQuestionTitle.text = representedQuestion.question
            tvQuestionAuthor.text = representedQuestion.author
            tvQuestionDescription.text = representedQuestion.detailedQuestion
        }
    }

    private fun setPositiveButtonBehaviour() {
        binding.btnAccept.setOnClickListener {
            //TODO sending data to backend...
            dismiss()
        }
    }

    private fun setNegativeButtonBehaviour() {
        binding.btnCancel.setOnClickListener {
            //TODO sending data to backend...
            dismiss()
        }
    }

}