package hu.bme.aut.android.together.features.event.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.DialogfragmentEventAttributeModifierBinding

class EventAttributeModifierDialogFragment(private val attributeName: String) : DialogFragment() {

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
        setPositiveButtonBehaviour()
        setNegativeButtonBehaviour()
    }

    private fun setTitleTextViewsContent() {
        binding.tvModificationTitle.text = getString(R.string.title_modification_event_attribute, attributeName)
    }

    private fun setPositiveButtonBehaviour() {
        //TODO saving the new attribute value
        binding.btnAccept.setOnClickListener {
            dismiss()
        }
    }

    private fun setNegativeButtonBehaviour() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}