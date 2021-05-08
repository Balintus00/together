package hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.participant.rulesetter.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentPublicEventRuleSetterBinding
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.participant.rulesetter.viewmodel.Loaded
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.participant.rulesetter.viewmodel.PublicEventRuleSetterState
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.participant.rulesetter.viewmodel.PublicEventRuleSetterViewModel

/**
 * On this Fragment the user can set the special public event options, such as the maximum
 * participant count, or the join request auto-accept options.
 */
@AndroidEntryPoint
class PublicEventRuleSetterFragment :
    RainbowCakeFragment<PublicEventRuleSetterState, PublicEventRuleSetterViewModel>() {

    companion object {
        const val EVENT_RULE_OPTIONS_RESULT_KEY = "EVENT_RULE_OPTIONS_RESULT_KEY"
    }

    private val publicEventRuleSetterViewModel: PublicEventRuleSetterViewModel by viewModels()

    private val args: PublicEventRuleSetterFragmentArgs by navArgs()

    private lateinit var binding: FragmentPublicEventRuleSetterBinding

    override fun provideViewModel(): PublicEventRuleSetterViewModel = publicEventRuleSetterViewModel

    override fun render(viewState: PublicEventRuleSetterState) {
        when (viewState) {
            is Loaded -> {
            }
        }.exhaustive
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPublicEventRuleSetterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialViewState()
        setWidgetBehaviours()
        observeViewModel()
    }

    private fun setInitialViewState() {
        publicEventRuleSetterViewModel.publicEventOptions.postValue(args.options)
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
                R.id.rbAutoRequestHandlingPositive -> {
                    publicEventRuleSetterViewModel.publicEventOptions.value!!.isJoinAutoAcceptEnabled =
                        true
                    notifyViewModel()
                }
                R.id.rbAutoRequestHandlingNegative -> {
                    publicEventRuleSetterViewModel.publicEventOptions.value!!.isJoinAutoAcceptEnabled =
                        false
                    notifyViewModel()
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
     * it should be navigated to a [hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.participant.quantifier.fragment.PublicEventParticipantQuantifierFragment]
     * instance, on which the user can specify the count, that should be used as threshold.
     */
    private fun setCountOptionBehaviour() {
        binding.tvParticipantCountOption.setOnClickListener {
            //TODO this fragment will be started for result
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
        binding.btnPublicEventRuleSetterNext.setOnClickListener {
            with(findNavController()) {
                previousBackStackEntry?.savedStateHandle?.set(
                    EVENT_RULE_OPTIONS_RESULT_KEY,
                    publicEventRuleSetterViewModel.publicEventOptions.value
                )
                popBackStack()
            }
        }
    }

    private fun notifyViewModel() {
        publicEventRuleSetterViewModel.publicEventOptions.value =
            publicEventRuleSetterViewModel.publicEventOptions.value
    }

    private fun observeViewModel() {
        publicEventRuleSetterViewModel.publicEventOptions.observe(viewLifecycleOwner) {
            setAutoJoinRadioGroup(it.isJoinAutoAcceptEnabled)
            setParticipantCountViews(it.isParticipantCountLimited, it.maximumParticipantCount)
        }
    }

    private fun setParticipantCountViews(isMaximumRuleSet: Boolean, maximumParticipantCount: Int) {
        if (isMaximumRuleSet) {
            setParticipantCountViewsIntoNoRuleState()
        } else {
            setParticipantCountViewsIntoRuleSetState(maximumParticipantCount)
        }
    }

    private fun setParticipantCountViewsIntoNoRuleState() {
        with(binding) {
            tvParticipantCountOption.text =
                resources.getString(R.string.participant_option_infinite)
        }
    }

    private fun setParticipantCountViewsIntoRuleSetState(maxParticipantCount: Int) {
        with(binding) {
            tvParticipantCountOption.text = maxParticipantCount.toString()
        }
    }

    private fun setAutoJoinRadioGroup(isEnabled: Boolean) {
        binding.rbAutoRequestHandlingPositive.isChecked = isEnabled
        binding.rbAutoRequestHandlingNegative.isChecked = !isEnabled
    }
}