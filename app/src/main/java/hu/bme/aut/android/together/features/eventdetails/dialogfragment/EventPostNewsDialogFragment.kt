package hu.bme.aut.android.together.features.eventdetails.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.together.databinding.DialogfragmentEventPostNewsBinding

class EventPostNewsDialogFragment : DialogFragment() {

    private lateinit var binding: DialogfragmentEventPostNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogfragmentEventPostNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogFragmentUI()
    }

    private fun setDialogFragmentUI() {
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        setPostButtonBehaviour()
        setCancelButtonBehaviour()
    }

    private fun setPostButtonBehaviour() {
        binding.btnPost.setOnClickListener{
            //TODO this will start a network request
            dismiss()
        }
    }

    private fun setCancelButtonBehaviour() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

}