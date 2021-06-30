package hu.bme.aut.android.together.ui.screen.addevent.pagerelement.detailsetter.participant.quantifier.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.databinding.FragmentPublicEventParticipantQuantifierBinding
import hu.bme.aut.android.together.ui.screen.addevent.pagerelement.detailsetter.participant.quantifier.dialogfragment.EventParticipantCountSpecifierDialogFragment
import hu.bme.aut.android.together.ui.model.EventParticipantCountOptions

/**
 * On this Fragment the user can specify the maximum threshold for participants.
 */
class PublicEventParticipantQuantifierFragment : Fragment() {

    companion object{
        const val PARTICIPANT_QUANTIFIER_RESULT_KEY = "PARTICIPANT_QUANTIFIER_RESULT_KEY"
    }

    private var _binding: FragmentPublicEventParticipantQuantifierBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeMaxCountResult()
    }

    private fun observeMaxCountResult() {
        setFragmentResultListener(EventParticipantCountSpecifierDialogFragment.PARTICIPANT_COUNT_SPECIFIER_DIALOG_FRAGMENT_RESULT_KEY) {
            _, bundle ->
            passResultAndNavigateBack(true,
                bundle.getInt(EventParticipantCountSpecifierDialogFragment.PARTICIPANT_COUNT_SPECIFIER_DIALOG_FRAGMENT_BUNDLE_KEY)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
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
        binding.tvInfiniteParticipantOption.setOnClickListener {
            passResultAndNavigateBack(false, 0)
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

    private fun passResultAndNavigateBack(isMaximumParticipantCountRuleSet: Boolean, newMaxParticipantCount: Int) {
        with(findNavController()) {
            previousBackStackEntry?.savedStateHandle?.set(
                PARTICIPANT_QUANTIFIER_RESULT_KEY, EventParticipantCountOptions(isMaximumParticipantCountRuleSet, newMaxParticipantCount)
            )
            popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}