package hu.bme.aut.android.together.features.profile.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.together.databinding.DialogfragmentInvitationResponderBinding
import hu.bme.aut.android.together.model.EventNewsMessage

class InvitationResponderDialogFragment(val invitationData: EventNewsMessage) : DialogFragment() {

    private lateinit var binding: DialogfragmentInvitationResponderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogfragmentInvitationResponderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        setPositiveButtonBehaviour()
        setNegativeButtonBehaviour()
    }

    private fun setPositiveButtonBehaviour() {
        binding.btnAccept.setOnClickListener {
            //TODO accepting...
            dismiss()
        }
    }

    private fun setNegativeButtonBehaviour() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

}