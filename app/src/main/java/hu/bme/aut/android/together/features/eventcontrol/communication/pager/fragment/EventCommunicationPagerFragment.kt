package hu.bme.aut.android.together.features.eventcontrol.communication.pager.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentEventCommunicationPagerBinding
import hu.bme.aut.android.together.features.eventcontrol.communication.pager.adapter.CommunicationPanelsAdapter
import hu.bme.aut.android.together.features.eventcontrol.communication.pager.viewmodel.DataLoaded
import hu.bme.aut.android.together.features.eventcontrol.communication.pager.viewmodel.EventCommunicationPagerState
import hu.bme.aut.android.together.features.eventcontrol.communication.pager.viewmodel.EventCommunicationPagerViewModel
import hu.bme.aut.android.together.features.eventcontrol.communication.pager.viewmodel.Loading
import hu.bme.aut.android.together.model.presentation.CommunicationPagerData

/**
 * This fragment provides an user interface, which can be used to connect with the event's other
 * participants. Organisers can post news, and answer questions about the event. Participants
 * can ask questions about this event, and read the organisers' news posts.
 */
@AndroidEntryPoint
class EventCommunicationPagerFragment :
    RainbowCakeFragment<EventCommunicationPagerState, EventCommunicationPagerViewModel>() {

    private val args: EventCommunicationPagerFragmentArgs by navArgs()

    private lateinit var binding: FragmentEventCommunicationPagerBinding

    private lateinit var pagerAdapter: CommunicationPanelsAdapter

    private val eventCommunicationPagerViewModel: EventCommunicationPagerViewModel by viewModels()

    override fun provideViewModel(): EventCommunicationPagerViewModel = eventCommunicationPagerViewModel

    override fun render(viewState: EventCommunicationPagerState) {
        when(viewState){
            is Loading -> {
                displayLoadingUI()
            }
            is DataLoaded -> {
                showLoadedUI(viewState.data)
            }
        }.exhaustive
    }

    private fun displayLoadingUI() {
        clearToolbarMenu()
    }

    private fun clearToolbarMenu() {
        binding.tbEventDetailsCommunication.menu.clear()
    }

    private fun showLoadedUI(data: CommunicationPagerData) {
        setUpLoadedToolbar(data)
    }

    private fun setUpLoadedToolbar(data: CommunicationPagerData) {
        setToolbarTitle(data.eventTitle)
        setUpOrganisersToolbarMenuIfAuthorized(data.isOrganiser, data.organiserQuestionCount)
    }

    private fun setToolbarTitle(eventTitle: String) {
        binding.tbEventDetailsCommunication.title = eventTitle
    }

    /**
     * If the user has organiser privilege, it can access an organiser specific menu on the toolbar;
     * this function sets this behaviour.
     */
    private fun setUpOrganisersToolbarMenuIfAuthorized(isOrganiser: Boolean, organiserQuestionCount: Int) {
        if (isOrganiser)
            addOrganiserActionsToToolbar(organiserQuestionCount)
    }

    /**
     * The organisers can use an inbox to check and answer incoming questions. This function sets
     * up this menu on the toolbar, and its inbox action's onclick behaviour.
     */
    // The function that uses this feature is already marked with this annotation, and it should be enough
    @SuppressLint("UnsafeExperimentalUsageError")
    private fun addOrganiserActionsToToolbar(organiserQuestionCount: Int) {
        with(binding.tbEventDetailsCommunication) {
            inflateMenu(R.menu.organiser_incoming_questions_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.actionOrganiserEventQuestions -> {
                        EventCommunicationPagerFragmentDirections.actionEventDetailsCommunicationFragmentToEventQuestionsFragment()
                            .let { action ->
                                findNavController().navigate(action)
                            }
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }
        addQuestionCountBadge(organiserQuestionCount)
    }

    /**
     * Attaches badge to the organiser specific menu's inbox action. The badge represents the
     * count of incoming unanswered questions.
     */
    @com.google.android.material.badge.ExperimentalBadgeUtils
    private fun addQuestionCountBadge(organiserQuestionCount: Int) {
        BadgeDrawable.create(requireContext()).apply {
            isVisible = true
            number = organiserQuestionCount
        }.let { badge ->
            BadgeUtils.attachBadgeDrawable(
                badge,
                binding.tbEventDetailsCommunication,
                R.id.actionOrganiserEventQuestions
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventCommunicationPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpTabBehaviour()
    }

    private fun setUpToolbar() {
        setNavigationActionBehaviour()
    }

    /**
     * Sets the toolbar navigation icon's onclick behaviour. After the icon was clicked,
     * the BackStack is popped.
     */
    private fun setNavigationActionBehaviour() {
        binding.tbEventDetailsCommunication.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpTabBehaviour() {
        initializeAndSetPagerAdapter()
        attachTabLayoutMediator()
    }

    private fun initializeAndSetPagerAdapter() {
        pagerAdapter = CommunicationPanelsAdapter(this, args.eventId)
        binding.vp2CommunicationPanels.adapter = pagerAdapter
    }

    /**
     * Attaches a TabLayoutMediator to the contained ViePager2 and TabLayout widget.
     * The tab's name are generated by calling [pagerAdapter]'s [CommunicationPanelsAdapter.getTabName]
     * function.
     */
    private fun attachTabLayoutMediator() {
        TabLayoutMediator(
            binding.tlCommunicationPanels,
            binding.vp2CommunicationPanels
        ) { tab, position ->
            tab.text = pagerAdapter.getTabName(position)
        }.attach()
    }

    override fun onStart() {
        super.onStart()
        eventCommunicationPagerViewModel.loadRepresentedData(args.eventId)
    }
}