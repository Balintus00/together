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

/**
 * On this fragment the user can modify an event.
 */
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

    /**
     * Sets the Toolbar's navigation icon onclick behaviour.
     * When the icon is clicked, a dialog will be displayed, that asks the user's confirmation
     * for this action.
     */
    private fun setUpToolBarBehaviour() {
        with(binding.tbModifyEvent) {
            setNavigationOnClickListener {
                displayAreYouSureDialogBeforeClosing()
            }
        }
    }

    /**
     * Displays an [AlertDialog], that asks for the user's confirmation of the modification
     * action's abort. If the user abort the modification, the BackStack will be popped.
     */
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

    /**
     * The FAB on this is used to save the modification of the event.
     * Clicking this FAB saves the event, then pops the BackStack to navigate back to the
     * event's details screen.
     */
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

    /**
     * Displays an [AlertDialog] which can be used to modify the content of TextView given as
     * parameter.
     * @param textView the TextView, of which content can be modified using the dialog.
     */
    private fun setOnClickDialogToAppearOnTextView(textView: TextView) {
        textView.setOnClickListener {
            //TODO showing actual AlertDialog
            AlertDialog.Builder(requireContext()).apply {
                setMessage("Modification dialog will appear here!")
            }.show()
        }
    }
}