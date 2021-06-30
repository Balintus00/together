package hu.bme.aut.android.together.ui.screen.searchevent.searcher.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentEventQueryBinding
import hu.bme.aut.android.together.ui.screen.searchevent.searcher.viewmodel.EventQueryState
import hu.bme.aut.android.together.ui.screen.searchevent.searcher.viewmodel.EventQueryViewModel
import hu.bme.aut.android.together.ui.model.EventQueryParameter
import java.util.*

/**
 * With this Fragment the user can specify event search parameters to use to discover new events.
 * After triggering the search (Using the Fragment's Floating Action Button), the user will be
 * navigated to a [hu.bme.aut.android.together.features.searchevent.result.fragment.EventSearchResultFragment]
 * instance, which will contain the search results.
 */
@AndroidEntryPoint
class EventQueryFragment : RainbowCakeFragment<EventQueryState, EventQueryViewModel>() {

    private var _binding: FragmentEventQueryBinding? = null

    private val binding get() = _binding!!

    private val eventQueryViewModel: EventQueryViewModel by viewModels()

    override fun provideViewModel(): EventQueryViewModel = eventQueryViewModel

    override fun render(viewState: EventQueryState) {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_query, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = eventQueryViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        setDateAndTimeSetterTextViews()
        setUpCategorySpinner()
        setUpSearchFAB()
    }

    /**
     * The Fragment contains multiple TextViews (by their ids: tvFromDate, tvFromTime, tvToDate,
     * tvToTime), which should be used to apply date and time filters to the query.
     * This function applies this behaviour to those TextViews.
     */
    //TODO it was easy to refactor this function like this; might not be the best solution...
    private fun setDateAndTimeSetterTextViews() {
        setDateTextViewDialogBehaviour(
            binding.tvFromDate,
            getString(R.string.event_query_default_value_today),
            this::useDatePickerDialogOnTextView
        ) {
            viewModel.startDateText.value = it
        }
        setDateTextViewDialogBehaviour(
            binding.tvFromTime,
            getString(R.string.event_query_default_value_now),
            this::useTimePickerDialogOnTextView
        ) {
            viewModel.startTimeText.value = it
        }
        setDateTextViewDialogBehaviour(
            binding.tvToDate,
            getString(R.string.event_query_default_value_date_indefinite),
            this::useDatePickerDialogOnTextView
        ) {
            viewModel.endDateText.value = it
        }
        setDateTextViewDialogBehaviour(
            binding.tvToTime,
            getString(R.string.event_query_default_value_time_indefinite),
            this::useTimePickerDialogOnTextView
        ) {
            viewModel.endTimeText.value = it
        }
    }

    /**
     * This function sets the on click behaviour of the given TextView. When the TextView will be
     * clicked, an [AlertDialog] will be displayed to the user, on which the user can reset
     * the TextView's content to the given default value, or can choose to use the given method
     * reference to set the TextView's content.
     * @param dateTimeTextView the TextViews, of which behaviour will be set.
     * @param defaultTextValue the default value, which can be applied to the TextView's content
     * by the user's choice.
     * @param displaySetterDialog this method reference is called, when the user wants to set the
     * value of the given TextView, but not to the given default value. This function should provide
     * some kind of interface to the user, on which the user can select the value, which should be
     * applied as the TextView's content.
     */
    private fun setDateTextViewDialogBehaviour(
        dateTimeTextView: TextView,
        defaultTextValue: String,
        displaySetterDialog: ((String) -> Unit) -> Unit,
        changeText: (String) -> Unit
    ) {
        dateTimeTextView.setOnClickListener {
            val dateSettingChoiceArray =
                resources.getStringArray(R.array.event_query_date_setting_options)
            AlertDialog.Builder(requireContext()).apply {
                setItems(dateSettingChoiceArray) { dialog: DialogInterface, i: Int ->
                    when (i) {
                        0 -> {
                            displaySetterDialog(changeText)
                            dialog.dismiss()
                        }
                        1 -> {
                            changeText(defaultTextValue)
                            dialog.dismiss()
                        }
                        else -> dialog.dismiss()
                    }
                }
            }.show()
        }
    }

    /**
     * This function displays a [DatePickerDialog], of which result will be passed to the given
     * text changer function.
     * @param changeText the function, that uses the result of the dialog.
     */
    private fun useDatePickerDialogOnTextView(changeText: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                changeText(getString(R.string.date_year_month_day, year, month + 1, day))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    /**
     * This function displays a [TimePickerDialog], of which result will be passed to the given
     * text changer function.
     * @param changeText the function, that uses the result of the dialog.
     */
    private fun useTimePickerDialogOnTextView(changeText: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            requireContext(),
            { _, hour, minute ->
                changeText(getString(R.string.time_hour_minute, hour, minute))
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    /**
     * This function initializes the Spinner's (with the id of spinnerCategory) ArrayAdapter, to
     * contain the possible event type values, and also a value, which the user can select, if it
     * doesn't want to use the category filter.
     */
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

    /**
     * This function sets the FAB's (with the id of fabSearchEvent) behaviour when clicked.
     * When the FAB is clicked, the search parameters (in this fragment's widgets) specified by the
     * user will be passed to a [hu.bme.aut.android.together.features.searchevent.result.fragment.EventSearchResultFragment]
     * instance, then the application navigates to this fragment.
     */
    private fun setUpSearchFAB() {
        binding.fabSearchEvent.setOnClickListener {
            EventQueryFragmentDirections.actionEventQueryFragmentToEventSearchResultFragment(
                EventQueryParameter(
                    place = binding.etPlace.text.toString(),
                    radius = if (binding.etRadius.text.isEmpty()) 0 else binding.etRadius.text.toString()
                        .toInt(),
                    startDate = binding.tvFromDate.text.toString(),
                    startTime = binding.tvFromTime.text.toString(),
                    endDate = binding.tvToDate.text.toString(),
                    endTime = binding.tvToTime.text.toString(),
                    type = binding.spinnerCategory.selectedItem.toString(),
                    name = binding.etName.text.toString()
                )
            )
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}