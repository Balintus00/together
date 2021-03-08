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

    private fun setChosenCard(card: MaterialCardView) {
        // To achieve pixel-independent display, pixel value should be calculated from given dp value
        card.strokeWidth = CHOSEN_CARD_STROKE_WIDTH_DP.convertDpToPixel()
        card.invalidate()
    }

    private fun Int.convertDpToPixel(): Int {
        return (this.toFloat() * requireContext().resources.displayMetrics.densityDpi.toFloat() / 160.0f).roundToInt()
    }

    private fun setUnchosenCard(card: MaterialCardView) {
        card.strokeWidth = 0
        card.invalidate()
    }

    private fun setPublicOptionsVisibility(willBeVisible: Boolean) {
        with(binding.clPublicOptions) {
            if (willBeVisible) {
                visibility = View.VISIBLE
                requestFocus()
            } else
                visibility = View.GONE
        }
    }

    private fun setPublicOptionsLinkBehaviour() {
        binding.tvPublicOptionsModifierLink.setOnClickListener {
            // TODO this parameter should come from DI pattern
            AddEventPagerFragmentDirections.actionAddEventPagerFragmentToPublicEventRuleSetterFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }
}