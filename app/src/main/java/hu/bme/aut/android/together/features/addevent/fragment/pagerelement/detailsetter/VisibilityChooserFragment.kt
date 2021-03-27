package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import hu.bme.aut.android.together.databinding.FragmentVisibilityChooserBinding
import hu.bme.aut.android.together.features.addevent.fragment.AddEventPagerFragmentDirections
import kotlin.math.roundToInt

/**
 * This [Fragment] provides an user interface, that can be used by the user to set the visibility of
 * the event which is under creation.
 */
class VisibilityChooserFragment : Fragment() {

    companion object {
        private const val CHOSEN_CARD_STROKE_WIDTH_DP = 5
    }

    private lateinit var binding: FragmentVisibilityChooserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVisibilityChooserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidgetsBehaviour()
    }

    private fun setWidgetsBehaviour() {
        setCardsClickBehaviour()
        setPublicOptionsLinkBehaviour()
    }

    private fun setCardsClickBehaviour() {
        with(binding) {
            setChoosableClickBehaviourOnCard(cardPrivate, arrayOf(cardPublic), false)
            setChoosableClickBehaviourOnCard(cardPublic, arrayOf(cardPrivate), true)
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
        willPublicOptionsBeVisible: Boolean
    ) {
        chosenCard.setOnClickListener { card ->
            setChosenCard(card as MaterialCardView)
            for (otherCard in otherCards)
                setUnchosenCard(otherCard)
            setPublicOptionsVisibility(willPublicOptionsBeVisible)
        }
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
            visibility = if (willBeVisible) {
                View.VISIBLE
            } else
                View.GONE
        }
    }

    /**
     * Sets the underlined TextView's onclick behaviour in the special public options.
     * If the user clicks this TextView, it will be navigated to a different Fragment, on which
     * it can set these special options.
     */
    private fun setPublicOptionsLinkBehaviour() {
        binding.tvPublicOptionsModifierLink.setOnClickListener {
            AddEventPagerFragmentDirections.actionAddEventPagerFragmentToPublicEventRuleSetterFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }
}