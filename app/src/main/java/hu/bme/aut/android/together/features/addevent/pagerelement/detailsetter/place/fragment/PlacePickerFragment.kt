package hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.place.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentPlacePickerBinding
import hu.bme.aut.android.together.features.addevent.pagerelement.settercontainer.modificationcallback.ModificationCallback

/**
 * This [Fragment] provides an user interface, that can be used by the user to set the location of
 * the event which is under creation.
 */
class PlacePickerFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var modificationCallback: ModificationCallback

    private lateinit var binding: FragmentPlacePickerBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initializeModificationCallback()
    }

    private fun initializeModificationCallback() {
        modificationCallback = parentFragment as ModificationCallback
    }

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
        observePlaceAndNotifyModificationCallback()
    }

    private fun setUpUIWidgets() {
        setCategorySpinnerBehaviour()
        setInitialState()
    }

    /**
     * Sets the contained Spinner's ArrayAdapter. The ArrayAdapter is using this resource as its
     * content [R.array.add_event_pick_place_categories].
     */
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

    private fun setInitialState() {
        val currentLocation = modificationCallback.getLocation()
        if(currentLocation == getString(R.string.online)) {
            binding.spinnerAddEventPickPlaceCategories.setSelection(1)
        } else {
            binding.spinnerAddEventPickPlaceCategories.setSelection(0)
        }
    }

    private fun observePlaceAndNotifyModificationCallback() {
        binding.etAddEventPickPlace.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {
                p0.toString().let {
                    if(it != resources.getString(R.string.online))
                        modificationCallback.setLocation(it)
                }
            }

        })
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        when (position) {
            // Not online
            0 -> {
                binding.etAddEventPickPlace.isEnabled = true
                binding.etAddEventPickPlace.setText(modificationCallback.getLocation())
            }
            // Online
            1 -> {
                binding.etAddEventPickPlace.isEnabled = false
                binding.etAddEventPickPlace.setText(getString(R.string.online))
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) { }
}