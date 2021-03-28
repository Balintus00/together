package hu.bme.aut.android.together.features.eventdetails.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.together.databinding.DialogfragmentEventQuestionResponseBinding
import hu.bme.aut.android.together.model.EventNewsMessage

class EventQuestionResponseDialogFragment(private val representedQuestion: EventNewsMessage) : DialogFragment() {

    private lateinit var binding: DialogfragmentEventQuestionResponseBinding

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
            tvQuestionTitle.text = representedQuestion.title
            tvQuestionAuthor.text = representedQuestion.author
            tvQuestionDescription.text = representedQuestion.message
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