package hu.bme.aut.android.together.features.event.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.together.databinding.DialogfragmentEventQuestionAskingBinding

class EventQuestionAskingDialogFragment : DialogFragment() {

    private lateinit var binding: DialogfragmentEventQuestionAskingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogfragmentEventQuestionAskingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUIWidgetsBehaviour()
    }

    private fun setUIWidgetsBehaviour() {
        setPositiveButtonBehaviour()
        setNegativeButtonBehaviour()
    }

    private fun setPositiveButtonBehaviour() {
        binding.btnAccept.setOnClickListener {
            //TODO processing data
            dismiss()
        }
    }
    private fun setNegativeButtonBehaviour() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}