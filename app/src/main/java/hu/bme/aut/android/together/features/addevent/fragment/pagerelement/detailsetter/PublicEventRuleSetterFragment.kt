package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentPublicEventRuleSetterBinding

/**
 * On this Fragment the user can set the special public event options, such as the maximum
 * participant count, or the join request auto-accept options.
 */
class PublicEventRuleSetterFragment : Fragment() {

    private lateinit var binding: FragmentPublicEventRuleSetterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPublicEventRuleSetterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidgetBehaviours()
    }

    private fun setWidgetBehaviours() {
        setRequestHandlingRadioGroupBehaviour()
        setCountOptionBehaviour()
        setApplyButtonBehaviour()
    }

    /**
     * The request auto-accept options can be set on a RadioGroup.
     * By default, the apply button, that saves the user's chosen option is disabled. If one of the
     * RadioButton of RadioGroup gets selected, this button should be enabled.
     */
    private fun setRequestHandlingRadioGroupBehaviour() {
        binding.rgRequestHandlingOptions.setOnCheckedChangeListener { _, i ->
            when (i) {
                // TODO setting chosen data
                R.id.rbAutoRequestHandlingPositive -> {
                    binding.btnPublicEventRuleSetterNext.isEnabled = true
                }
                R.id.rbAutoRequestHandlingNegative -> {
                    binding.btnPublicEventRuleSetterNext.isEnabled = true
                }
                else ->
                    Log.w(
                        "Together!",
                        "PublicEventRuleSetterFragment invalid RadioGroup option was chosen! (Automatic request handling)"
                    )
            }
        }
    }

    /**
     * If the user clicks on the TextView, that contains the maximum participant count options,
     * it should be navigated to a [PublicEventParticipantQuantifierFragment] instance, on which
     * the user can specify the count, that should be used as threshold.
     */
    private fun setCountOptionBehaviour() {
        binding.tvParticipantCountOption.setOnClickListener {
            PublicEventRuleSetterFragmentDirections.actionPublicEventRuleSetterFragmentToPublicEventParticipantQuantifierFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }

    /**
     * When the Apply button is clicked, it should save the set options, then navigate back.
     */
    private fun setApplyButtonBehaviour() {
        //TODO setting the chosen data
        binding.btnPublicEventRuleSetterNext.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}