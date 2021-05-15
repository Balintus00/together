package hu.bme.aut.android.together.features.eventcontrol.wholedescription.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.databinding.FragmentEventDetailsWholeDescriptionBinding
import hu.bme.aut.android.together.features.eventcontrol.wholedescription.viewmodel.EventWholeDescriptionLoaded
import hu.bme.aut.android.together.features.eventcontrol.wholedescription.viewmodel.EventWholeDescriptionState
import hu.bme.aut.android.together.features.eventcontrol.wholedescription.viewmodel.EventWholeDescriptionViewModel
import hu.bme.aut.android.together.features.eventcontrol.wholedescription.viewmodel.Loading
import hu.bme.aut.android.together.model.presentation.EventDescriptionScreenData
import kotlin.properties.Delegates

/**
 * This fragment displays the whole description of an event.
 */
@AndroidEntryPoint
class EventWholeDescriptionFragment :
    RainbowCakeFragment<EventWholeDescriptionState, EventWholeDescriptionViewModel>() {

    private val args: EventWholeDescriptionFragmentArgs by navArgs()

    private var eventId by Delegates.notNull<Long>()

    private lateinit var binding: FragmentEventDetailsWholeDescriptionBinding

    private val eventWholeDescriptionViewModel: EventWholeDescriptionViewModel by viewModels()

    override fun provideViewModel(): EventWholeDescriptionViewModel = eventWholeDescriptionViewModel

    override fun render(viewState: EventWholeDescriptionState) {
        when(viewState) {
            is Loading -> {
                displayLoadingUI()
            }
            is EventWholeDescriptionLoaded -> {
                displayLoadedUI(viewState.descriptionData)
            }
        }.exhaustive
    }

    private fun displayLoadingUI() {
        with(binding) {
            clContent.isVisible = false
            cpiDescriptionLoading.isVisible = true
        }
    }

    private fun displayLoadedUI(loadedData: EventDescriptionScreenData) {
        displayContent(loadedData)
        with(binding) {
            cpiDescriptionLoading.isVisible = false
            clContent.isVisible = true
        }
    }

    private fun displayContent(content: EventDescriptionScreenData) {
        with(binding) {
            content.let {
                tbEventDetailsFull.title = it.title
                tvEventStartDateTime.text = it.startDateTime
                tvEventPlace.text = it.location
                tvEventDescription.text = it.description
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrieveEventIdFromNavArgs()
    }

    private fun retrieveEventIdFromNavArgs() {
        eventId = args.eventId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailsWholeDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
    }

    /**
     * Sets the contained Toolbar's navigation icon's behaviour.
     * When the navigation icon is clicked, then the BackStack will be popped.
     */
    private fun setUpToolbar() {
        with(binding.tbEventDetailsFull) {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        eventWholeDescriptionViewModel.loadDescriptionScreenData(eventId)
    }
}