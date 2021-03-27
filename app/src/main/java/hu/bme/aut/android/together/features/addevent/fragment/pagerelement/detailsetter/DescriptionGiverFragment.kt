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

/**
 * This [Fragment] provides an user interface, that can be used by the user to set description of
 * the event which is under creation.
 */
class DescriptionGiverFragment : Fragment() {

    /**
     * This variable stores the value, that how much character can be contained by the description.
     */
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

    /**
     * Sets the behaviour of the character counter TextView.
     */
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

    /**
     * Sets the value of the character counting TextView to the given parameter's value.
     * @param newCount the new content of the character counter TextView.
     */
    private fun setCharacterCounterTextView(newCount: Int) {
        binding.tvRemainingCharacterCount.text = newCount.toString()
    }

    /**
     * Sets the description container EditText's behaviour to change the content of the character
     * counter TextView whenever its content is modified.
     */
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