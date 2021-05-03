package hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.participant.quantifier.dialogfragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.DialogfragmentEventParticipantCountSpecifierBinding

class EventParticipantCountSpecifierDialogFragment : DialogFragment() {

    private lateinit var binding: DialogfragmentEventParticipantCountSpecifierBinding

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
        binding =
            DialogfragmentEventParticipantCountSpecifierBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpDialogFragmentUI()
    }

    private fun setUpDialogFragmentUI(){
        dialog?.let{
            setDialogAttributes(it)
        }
        setWidgetsBehaviour()
    }

    private fun setDialogAttributes(dialog: Dialog){
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
            // TODO: passing data using ViewModel probably.
            //  Also the passed data's validness should be checked.
            //  If something is wrong, the user can be notified here about it.
            isCountSpecified = true
            dismiss()
        }
    }

    private fun setCancelButtonBehaviour() {
        binding.btnCancelParticipantCount.setOnClickListener{
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if(isCountSpecified)
            findNavController().popBackStack()
    }

}