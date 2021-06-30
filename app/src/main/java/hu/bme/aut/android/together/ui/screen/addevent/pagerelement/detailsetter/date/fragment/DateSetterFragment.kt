package hu.bme.aut.android.together.ui.screen.addevent.pagerelement.detailsetter.date.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentDateSetterBinding
import hu.bme.aut.android.together.ui.screen.addevent.pagerelement.settercontainer.modificationcallback.ModificationCallback
import java.util.*

/**
 * This [Fragment] provides an user interface, that can be used by the user to set the dates of
 * the event which is under creation (beginning & end). Clicking the TextViews, that are containing
 * information about the date and time of the event will pop up and [TimePickerDialog] or [DatePickerDialog]
 * which the user can use to specify the date and time of the event.
 */
class DateSetterFragment : Fragment() {

    private var modificationCallback: ModificationCallback? = null

    private var _binding: FragmentDateSetterBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initializeModificationCallback()
    }

    private fun initializeModificationCallback() {
        modificationCallback = parentFragment as ModificationCallback?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDateSetterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUIBehaviour()
        setInitialViewState()
    }

    private fun setUIBehaviour() {
        setFromDateBehaviour()
        setFromTimeBehaviour()
        setToDateBehaviour()
        setToTimeBehaviour()
    }

    private fun setFromDateBehaviour() {
        binding.tvAddEventFromDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    getString(R.string.date_year_month_day, year, month + 1, day).let {
                        binding.tvAddEventFromDate.text = it
                        modificationCallback?.setStartDateString(it)
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setFromTimeBehaviour() {
        binding.tvAddEventFromHourMinute.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(
                requireContext(),
                { _, hour, minute ->
                    getString(R.string.time_hour_minute, hour, minute).let {
                        binding.tvAddEventFromHourMinute.text = it
                        modificationCallback?.setStartTimeString(it)
                    }

                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    private fun setToDateBehaviour() {
        binding.tvAddEventToDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    getString(R.string.date_year_month_day, year, month + 1, day).let {
                        binding.tvAddEventToDate.text = it
                        modificationCallback?.setEndDateString(it)
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setToTimeBehaviour() {
        binding.tvAddEventToHourMinute.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(
                requireContext(),
                { _, hour, minute ->
                    getString(R.string.time_hour_minute, hour, minute).let {
                        binding.tvAddEventToHourMinute.text = it
                        modificationCallback?.setEndTimeString(it)
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    private fun setInitialViewState() {
        with(binding) {
            modificationCallback.let {
                tvAddEventFromDate.text = it?.getStartDateString() ?: "2022.02.02."
                tvAddEventToDate.text = it?.getEndDateString() ?: "2022.02.02."
                tvAddEventFromHourMinute.text =it?.getStartTimeString() ?: "14:45"
                tvAddEventToHourMinute.text = it?.getEndTimeString() ?: "14:45"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}