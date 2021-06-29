package hu.bme.aut.android.together.ui.screen.addevent.pagerelement.detailsetter.visibility.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentVisibilityChooserBinding
import hu.bme.aut.android.together.ui.screen.addevent.pager.fragment.AddEventPagerFragmentDirections
import hu.bme.aut.android.together.ui.screen.addevent.pagerelement.detailsetter.participant.rulesetter.fragment.PublicEventRuleSetterFragment
import hu.bme.aut.android.together.ui.screen.addevent.pagerelement.settercontainer.modificationcallback.ModificationCallback
import hu.bme.aut.android.together.ui.model.EventPublicRuleOptions
import kotlin.math.roundToInt

/**
 * This [Fragment] provides an user interface, that can be used by the user to set the visibility of
 * the event which is under creation.
 */
class VisibilityChooserFragment : Fragment() {

    companion object {
        private const val CHOSEN_CARD_STROKE_WIDTH_DP = 5
    }

    private var modificationCallback: ModificationCallback? = null

    private lateinit var binding: FragmentVisibilityChooserBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        modificationCallback = parentFragment as ModificationCallback?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVisibilityChooserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePublicEventRuleSetterResult()
        setWidgetsBehaviour()
        setInitiallySelectedVisibility()
        setDisplayedPublicEventOptions()
    }

    private fun observePublicEventRuleSetterResult() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<EventPublicRuleOptions>(
            PublicEventRuleSetterFragment.EVENT_RULE_OPTIONS_RESULT_KEY
        )?.observe(viewLifecycleOwner) {
            modificationCallback?.changeJoinRequestAutoAcceptRule(it.isJoinAutoAcceptEnabled)
            modificationCallback?.changeMaxParticipantCountRule(it.isParticipantCountLimited)
            if (it.isParticipantCountLimited) {
                modificationCallback?.setMaxParticipantCount(it.maximumParticipantCount)
            }
            setDisplayedPublicEventOptions()
        }
    }

    private fun setDisplayedPublicEventOptions() {
        modificationCallback?.let {
            with(modificationCallback!!) {
                binding.tvMaxParticipantCount.text =
                    if (!isMaxParticipantCountRuleSet()) resources.getString(
                        R.string.option_indefinite
                    ) else getMaxParticipantCount().toString()
                binding.tvAutoJoinEnabilityState.text =
                    if (isJoinRequestAutoAcceptAllowed()) resources.getString(R.string.state_on) else resources.getString(
                        R.string.state_off
                    )
            }
        }
    }

    private fun setWidgetsBehaviour() {
        setCardsClickBehaviour()
        setPublicOptionsLinkBehaviour()
    }

    private fun setCardsClickBehaviour() {
        with(binding) {
            setChoosableClickBehaviourOnCard(cardPrivate, arrayOf(cardPublic), false) {
                modificationCallback?.changeEventPrivateMode(true)
            }
            setChoosableClickBehaviourOnCard(cardPublic, arrayOf(cardPrivate), true) {
                modificationCallback?.changeEventPrivateMode(false)
            }
        }
    }

    /**
     * This function sets the onclick behaviour of the two MaterialCard widgets, that represent
     * the visibility options.
     * When the user choose by clicking a MaterialCard, other cards should be unselected.
     * The special public visibility's option will be displayed, if the given paramter enables it.
     * @param chosenCard the currently chosen card.
     * @param otherCards other cards, that can't be chosen.
     * @param willPublicOptionsBeVisible decides whether the public visibility's special options
     * and its interface should be available to the user.
     */
    private fun setChoosableClickBehaviourOnCard(
        chosenCard: MaterialCardView,
        otherCards: Array<MaterialCardView>,
        willPublicOptionsBeVisible: Boolean,
        notifyAboutVisibilityChange: () -> Unit
    ) {
        chosenCard.setOnClickListener { card ->
            setOneCardChosen(
                card as MaterialCardView,
                otherCards,
                willPublicOptionsBeVisible,
                notifyAboutVisibilityChange
            )
        }
    }

    private fun setOneCardChosen(
        chosenCard: MaterialCardView,
        unchosenCardArray: Array<MaterialCardView>,
        willPublicOptionsBeVisible: Boolean,
        notifyAboutVisibilityChange: () -> Unit
    ) {
        setChosenCard(chosenCard)
        for (otherCard in unchosenCardArray)
            setUnchosenCard(otherCard)
        setPublicOptionsVisibility(willPublicOptionsBeVisible)
        notifyAboutVisibilityChange()
    }

    /**
     * Adds border to chosen card.
     * If a card has thick border around it, then it implies that it is the selected card.
     * @param card the chosen [MaterialCardView] by the user, that represents a visibility option.
     */
    private fun setChosenCard(card: MaterialCardView) {
        // To achieve pixel-independent display, pixel value should be calculated from given dp value
        card.strokeWidth = CHOSEN_CARD_STROKE_WIDTH_DP.convertDpToPixel()
        card.invalidate()
    }

    /**
     * This function converts a dp value to pixel value.
     * @receiver the value in dp, which should be converted to pixel value.
     */
    private fun Int.convertDpToPixel(): Int {
        return (this.toFloat() * requireContext().resources.displayMetrics.densityDpi.toFloat() / 160.0f).roundToInt()
    }

    /**
     * Removes the from the card the border (by setting its strokeWidth 0).
     * If a card has thick border around it, then it implies that it is the selected card.
     * @param card the card, that is no longer chosen.
     */
    private fun setUnchosenCard(card: MaterialCardView) {
        card.strokeWidth = 0
        card.invalidate()
    }

    /**
     * Sets visible or invisible the special options of the public visibility.
     * @param willBeVisible decides whether or not the public visibility's special option should be
     * visible.
     */
    private fun setPublicOptionsVisibility(willBeVisible: Boolean) {
        with(binding.clPublicOptions) {
            isVisible = willBeVisible
        }
    }

    /**
     * Sets the underlined TextView's onclick behaviour in the special public options.
     * If the user clicks this TextView, it will be navigated to a different Fragment, on which
     * it can set these special options.
     */
    private fun setPublicOptionsLinkBehaviour() {
        binding.tvPublicOptionsModifierLink.setOnClickListener {
            AddEventPagerFragmentDirections.actionAddEventPagerFragmentToPublicEventRuleSetterFragment(
                modificationCallback?.let {
                    EventPublicRuleOptions(
                        it.isMaxParticipantCountRuleSet(),
                        it.getMaxParticipantCount(),
                        it.isJoinRequestAutoAcceptAllowed()
                    )
                } ?: EventPublicRuleOptions(false, 0, false)
            )
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }

    private fun setInitiallySelectedVisibility() {
        with(binding) {
            modificationCallback?.let {
                if (modificationCallback!!.isEventInCurrentlyPrivateMode()) {
                    setOneCardChosen(cardPrivate, arrayOf(cardPublic), false) { }
                } else {
                    setOneCardChosen(cardPublic, arrayOf(cardPrivate), true) { }
                }
            }
        }
    }
}