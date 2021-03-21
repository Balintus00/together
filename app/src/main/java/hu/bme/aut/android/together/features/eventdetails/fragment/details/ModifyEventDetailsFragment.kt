package hu.bme.aut.android.together.features.eventdetails.fragment.details

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.databinding.FragmentModifyEventDetailsBinding

class ModifyEventDetailsFragment : Fragment() {

    private lateinit var binding: FragmentModifyEventDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModifyEventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        setUpToolBarBehaviour()
        setFabBehaviour()
        setSimpleModifiableTextViewsBehaviour()
    }

    private fun setUpToolBarBehaviour() {
        with(binding.tbModifyEvent) {
            setNavigationOnClickListener {
                displayAreYouSureDialogBeforeClosing()
            }
        }
    }

    private fun displayAreYouSureDialogBeforeClosing() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Are sure?")
            setMessage("Every modification will be discarded.")
            setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog?.dismiss() }
            setPositiveButton("Discard") { _, _ ->
                findNavController().popBackStack()
            }
        }.show()
    }

    private fun setFabBehaviour() {
        //TODO sending actual data to backend
        binding.fabModifyEvent.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setSimpleModifiableTextViewsBehaviour() {
        with(binding) {
            setOnClickDialogToAppearOnTextView(tvEventName)
            setOnClickDialogToAppearOnTextView(tvEventLocation)
            setOnClickDialogToAppearOnTextView(tvDescription)
        }
    }

    private fun setOnClickDialogToAppearOnTextView(textView: TextView) {
        textView.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setMessage("Modification dialog will appear here!")
            }.show()
        }
    }
}