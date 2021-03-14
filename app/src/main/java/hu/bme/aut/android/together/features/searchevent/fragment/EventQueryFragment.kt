package hu.bme.aut.android.together.features.searchevent.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentEventQueryBinding
import java.util.*

class EventQueryFragment : Fragment() {

    private lateinit var binding: FragmentEventQueryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventQueryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        setDateSetterTextViews()
        setUpCategorySpinner()
        setUpSearchFAB()
    }

    private fun setDateSetterTextViews() {
        setDateTextViewDialogBehaviour(
            binding.tvFromDate,
            getString(R.string.event_query_default_value_today),
            this::useDatePickerDialogOnTextView
        )
        setDateTextViewDialogBehaviour(
            binding.tvFromTime,
            getString(R.string.event_query_default_value_now),
            this::useTimePickerDialogOnTextView
        )
        setDateTextViewDialogBehaviour(
            binding.tvToDate,
            getString(R.string.event_query_default_value_date_indefinite),
            this::useDatePickerDialogOnTextView
        )
        setDateTextViewDialogBehaviour(
            binding.tvToTime,
            getString(R.string.event_query_default_value_time_indefinite),
            this::useTimePickerDialogOnTextView
        )
    }

    private fun setDateTextViewDialogBehaviour(
        dateTextView: TextView,
        defaultTextValue: String,
        displaySetterDialog: (TextView) -> Unit
    ) {
        dateTextView.setOnClickListener {
            val dateSettingChoiceArray =
                resources.getStringArray(R.array.event_query_date_setting_options)
            AlertDialog.Builder(requireContext()).apply {
                setItems(dateSettingChoiceArray) { dialog: DialogInterface, i: Int ->
                    when (i) {
                        0 -> {
                            displaySetterDialog(dateTextView)
                            dialog.dismiss()
                        }
                        1 -> {
                            dateTextView.text = defaultTextValue
                            dialog.dismiss()
                        }
                        else -> dialog.dismiss()
                    }
                }
            }.show()
        }
    }

    private fun useDatePickerDialogOnTextView(dateTextView: TextView) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _, year, month, day -> dateTextView.text = "$year.$month.$day" },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun useTimePickerDialogOnTextView(timeTextView: TextView) {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            requireContext(),
            { _, hour, minute -> timeTextView.text = "$hour:$minute" },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun setUpCategorySpinner() {
        binding.spinnerCategory.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            arrayOf(
                getString(R.string.default_no_category),
                *resources.getStringArray(R.array.event_category_types_array)
            )
        )
    }

    private fun setUpSearchFAB() {
        binding.fabSearchEvent.setOnClickListener {
            //TODO search parameters wil be passed here
            EventQueryFragmentDirections.actionEventQueryFragmentToEventSearchResultFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }

}