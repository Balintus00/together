package hu.bme.aut.android.together.features.addevent.fragment.pagerelement

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.databinding.FragmentNameAdderBinding

class NameAdderFragment : Fragment() {

    private lateinit var binding: FragmentNameAdderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNameAdderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidgetsBehaviour()
    }

    private fun setWidgetsBehaviour() {
        setEditTextBehaviour()
        setNextButtonBehaviour()
    }

    private fun setEditTextBehaviour() {
        binding.etAddEventName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {
                //TODO next button setting was here
            }

        })
    }

    private fun setNextButtonBehaviour() {
        //TODO navigation was here
    }

}