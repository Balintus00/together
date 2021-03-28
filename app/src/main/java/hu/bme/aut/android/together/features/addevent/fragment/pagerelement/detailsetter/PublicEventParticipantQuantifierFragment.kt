package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.databinding.FragmentPublicEventParticipantQuantifierBinding
import hu.bme.aut.android.together.features.addevent.dialogfragment.EventParticipantCountSpecifierDialogFragment

/**
 * On this Fragment the user can specify the maximum threshold for participants.
 */
class PublicEventParticipantQuantifierFragment : Fragment() {

    private lateinit var binding: FragmentPublicEventParticipantQuantifierBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentPublicEventParticipantQuantifierBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOptionsBehaviour()
    }

    private fun setOptionsBehaviour() {
        setIndefiniteOptionBehaviour()
        setSpecifyCountOptionBehaviour()
    }

    /**
     * Sets the threshold as indefinite value, then pops the BackStack.
     */
    private fun setIndefiniteOptionBehaviour() {
        // TODO passing the set value using ViewModel probably
        binding.tvInfiniteParticipantOption.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    /**
     * Displays an [EventParticipantCountSpecifierDialogFragment] instance, on which the user can
     * specify the maximum participant count.
     */
    private fun setSpecifyCountOptionBehaviour() {
        // TODO passing the set value using ViewModel probably
        binding.tvSpecifiyParticipantCountOption.setOnClickListener {
            EventParticipantCountSpecifierDialogFragment().show(
                parentFragmentManager,
                EventParticipantCountSpecifierDialogFragment::class.java.simpleName
            )
        }
    }
}