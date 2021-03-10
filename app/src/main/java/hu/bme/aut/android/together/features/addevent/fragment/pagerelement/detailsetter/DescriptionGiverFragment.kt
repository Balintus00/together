package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.databinding.FragmentDescriptionGiverBinding

class DescriptionGiverFragment : Fragment() {

    private var maxCharacterCount = -1

    private lateinit var binding: FragmentDescriptionGiverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionGiverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeCharacterCounterTextView()
        setEditTextBehaviour()
    }

    private fun initializeCharacterCounterTextView() {
        saveMaxCharacterCount()
        setCharacterCounterTextView(maxCharacterCount)
    }

    private fun saveMaxCharacterCount() {
        for (filter in binding.etAddEventDescriptionGiver.filters) {
            if (filter is InputFilter.LengthFilter) {
                maxCharacterCount = filter.max
            }
        }
    }

    private fun setCharacterCounterTextView(newCount: Int) {
        binding.tvRemainingCharacterCount.text = newCount.toString()
    }

    private fun setEditTextBehaviour() {
        binding.etAddEventDescriptionGiver.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                setCharacterCounterTextView(maxCharacterCount - (p0?.length ?: 0))
            }

        })
    }
}