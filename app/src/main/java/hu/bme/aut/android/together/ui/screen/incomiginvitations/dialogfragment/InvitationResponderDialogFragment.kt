package hu.bme.aut.android.together.ui.screen.incomiginvitations.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.together.databinding.DialogfragmentInvitationResponderBinding
import hu.bme.aut.android.together.ui.model.EventInvitation

//TODO will be refactored
class InvitationResponderDialogFragment(val invitationData: EventInvitation) : DialogFragment() {

    private var _binding: DialogfragmentInvitationResponderBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogfragmentInvitationResponderBinding.inflate(inflater, container, false)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}