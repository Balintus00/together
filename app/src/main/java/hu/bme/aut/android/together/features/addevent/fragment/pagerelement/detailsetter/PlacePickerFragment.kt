package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentPlacePickerBinding

class PlacePickerFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentPlacePickerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlacePickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        setCategorySpinnerBehaviour()
        setNextButtonBehaviour()
    }

    private fun setCategorySpinnerBehaviour() {
        val categoriesArray = resources.getStringArray(R.array.add_event_pick_place_categories)
        val spinnerArrayAdapter =
            ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                categoriesArray
            )
        binding.spinnerAddEventPickPlaceCategories.adapter = spinnerArrayAdapter
       binding.spinnerAddEventPickPlaceCategories.onItemSelectedListener = this
    }

    private fun setNextButtonBehaviour() {
        //TODO navigation was here
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        //TODO button enabling was here
        when(position){
            0 -> {
                binding.etAddEventPickPlace.isEnabled = true
                binding.etAddEventPickPlace.text.clear()
            }
            1 -> {
                binding.etAddEventPickPlace.isEnabled = false
                binding.etAddEventPickPlace.setText(getString(R.string.online))
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //TODO button disabling was here
    }
}