package hu.bme.aut.android.together.features.eventcontrol.modifyevent.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.DialogfragmentEventAttributeModifierBinding

class EventAttributeModifierDialogFragment : DialogFragment() {

    companion object {
        private const val ATTRIBUTE_NAME_KEY = "ATTRIBUTE_NAME_KEY"
        private const val INITIAL_VALUE_KEY = "INITIAL_VALUE_KEY"
        fun newInstance(
            attributeName: String,
            initialValue: String,
            modifyFunction: (String) -> Unit
        ): EventAttributeModifierDialogFragment {
            return EventAttributeModifierDialogFragment().apply {
                onPositiveFinish = modifyFunction
                arguments = Bundle().apply {
                    putString(ATTRIBUTE_NAME_KEY, attributeName)
                    putString(INITIAL_VALUE_KEY, initialValue)
                }
            }
        }
    }

    private lateinit var onPositiveFinish: (String) -> Unit

    private lateinit var binding: DialogfragmentEventAttributeModifierBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogfragmentEventAttributeModifierBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        setTitleTextViewsContent()
        setEditTextInitialValueFromArgs()
        setPositiveButtonBehaviour()
        setNegativeButtonBehaviour()
    }

    private fun setTitleTextViewsContent() {
        binding.tvModificationTitle.text = getString(
            R.string.title_modification_event_attribute, requireArguments().getString(
                ATTRIBUTE_NAME_KEY
            )
        )
    }

    private fun setEditTextInitialValueFromArgs() {
        binding.editText2.setText(requireArguments().getString(INITIAL_VALUE_KEY))
    }

    private fun setPositiveButtonBehaviour() {
        binding.btnAccept.setOnClickListener {
            onPositiveFinish(binding.editText2.text.toString())
            dismiss()
        }
    }

    private fun setNegativeButtonBehaviour() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}