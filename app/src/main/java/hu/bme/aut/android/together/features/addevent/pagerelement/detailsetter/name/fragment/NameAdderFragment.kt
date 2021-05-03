package hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.name.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.databinding.FragmentNameAdderBinding
import hu.bme.aut.android.together.features.addevent.pagerelement.settercontainer.modificationcallback.ModificationCallback

/**
 * This [Fragment] provides an user interface, that can be used by the user to set the name of
 * the event which is under creation.
 */
class NameAdderFragment : Fragment() {

    /**
     * This variable stores the maximum length of the event's name.
     */
    private var maxCharacterCount = -1

    private lateinit var modificationCallback: ModificationCallback

    private lateinit var binding: FragmentNameAdderBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        modificationCallback = parentFragment as ModificationCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNameAdderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeCharacterCounterTextView()
        setEditTextBehaviour()
        setEditTextInitialValue()
    }

    /**
     * Sets the behaviour of the character counter textview.
     */
    private fun initializeCharacterCounterTextView() {
        saveMaxCharacterCount()
        setCharacterCounterTextView(maxCharacterCount)
    }

    /**
     * Saves the maximum value of the character counter into [maxCharacterCount].
     */
    private fun saveMaxCharacterCount() {
        for(filter in binding.etAddEventName.filters){
            if(filter is InputFilter.LengthFilter){
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
        binding.etAddEventName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {
                setCharacterCounterTextView(maxCharacterCount - (p0?.length ?: 0))
                modificationCallback.modifyEventTitle(p0.toString())
            }

        })
    }

    private fun setEditTextInitialValue() {
        binding.etAddEventName.setText(modificationCallback.getCurrentEventTitle())
    }

}