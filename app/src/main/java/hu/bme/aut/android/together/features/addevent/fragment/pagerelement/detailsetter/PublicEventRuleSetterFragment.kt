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
        setNextButtonBehaviour()
    }

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

    private fun setCountOptionBehaviour() {
        binding.tvParticipantCountOption.setOnClickListener {
            PublicEventRuleSetterFragmentDirections.actionPublicEventRuleSetterFragmentToPublicEventParticipantQuantifierFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }

    private fun setNextButtonBehaviour() {
        binding.btnPublicEventRuleSetterNext.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}