package hu.bme.aut.android.together.ui.screen.addevent.pagerelement.detailsetter.participant.quantifier.dialogfragment

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.DialogfragmentEventParticipantCountSpecifierBinding

class EventParticipantCountSpecifierDialogFragment : DialogFragment() {

    companion object {
        const val PARTICIPANT_COUNT_SPECIFIER_DIALOG_FRAGMENT_RESULT_KEY =
            "PARTICIPANT_COUNT_SPECIFIER_DIALOG_FRAGMENT_RESULT_KEY"
        const val PARTICIPANT_COUNT_SPECIFIER_DIALOG_FRAGMENT_BUNDLE_KEY =
            "PARTICIPANT_COUNT_SPECIFIER_DIALOG_FRAGMENT_BUNDLE_KEY"
        private const val DEFAULT_MAX_PARTICIPANT_COUNT_VALUE = 1
    }

    private var _binding: DialogfragmentEventParticipantCountSpecifierBinding? = null

    private val binding get() = _binding!!

    private var isCountSpecified = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDialogStyle()
    }

    private fun setDialogStyle() {
        setStyle(STYLE_NORMAL, R.style.DialogWithTitle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DialogfragmentEventParticipantCountSpecifierBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpDialogFragmentUI()
    }

    private fun setUpDialogFragmentUI() {
        dialog?.let {
            setDialogAttributes(it)
        }
        setWidgetsBehaviour()
    }

    private fun setDialogAttributes(dialog: Dialog) {
        dialog.setTitle(getString(R.string.title_event_participant_count_dialogfragment))
    }

    private fun setWidgetsBehaviour() {
        setEditTextBehaviour()
        setApplyButtonBehaviour()
        setCancelButtonBehaviour()
    }

    private fun setEditTextBehaviour() {
        binding.etParticipantCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length ?: 0 == 1 && p0.toString() == "0")
                    binding.etParticipantCount.setText("")
            }

        })
    }

    private fun setApplyButtonBehaviour() {
        binding.btnApplyParticipantCount.setOnClickListener {
            setFragmentResult(
                PARTICIPANT_COUNT_SPECIFIER_DIALOG_FRAGMENT_RESULT_KEY,
                bundleOf(PARTICIPANT_COUNT_SPECIFIER_DIALOG_FRAGMENT_BUNDLE_KEY to getSetMaxParticipantCount())
            )
            isCountSpecified = true
            dismiss()
        }
    }

    private fun getSetMaxParticipantCount(): Int {
        return binding.etParticipantCount.text.toString().let {
            try{
                it.toInt()
            } catch (nfe: NumberFormatException) {
                DEFAULT_MAX_PARTICIPANT_COUNT_VALUE
            }
        }
    }

    private fun setCancelButtonBehaviour() {
        binding.btnCancelParticipantCount.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}