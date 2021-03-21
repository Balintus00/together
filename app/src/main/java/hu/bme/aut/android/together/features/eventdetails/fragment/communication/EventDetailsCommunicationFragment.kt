package hu.bme.aut.android.together.features.eventdetails.fragment.communication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.tabs.TabLayoutMediator
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentEventDetailsCommunicationBinding
import hu.bme.aut.android.together.features.eventdetails.adapter.CommunicationPanelsAdapter

class EventDetailsCommunicationFragment : Fragment() {

    private val args: EventDetailsCommunicationFragmentArgs by navArgs()

    private lateinit var binding: FragmentEventDetailsCommunicationBinding

    private lateinit var pagerAdapter: CommunicationPanelsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailsCommunicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpTabBehaviour()
    }

    private fun setUpToolbar() {
        setUpToolbarMenu()
        setNavigationActionBehaviour()
    }

    private fun setUpToolbarMenu() {
        if (args.isOrganiser)
            addQuestionsActionToToolbar()
    }

    // The function that uses this feature is already marked with this annotation, and it should be enough
    @SuppressLint("UnsafeExperimentalUsageError")
    private fun addQuestionsActionToToolbar() {
        with(binding.tbEventDetailsCommunication) {
            inflateMenu(R.menu.organiser_event_questions_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.actionOrganiserEventQuestions -> {
                        EventDetailsCommunicationFragmentDirections.actionEventDetailsCommunicationFragmentToEventQuestionsFragment()
                            .let { action ->
                                findNavController().navigate(action)
                            }
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }
        addQuestionCountBadge()
    }

    @com.google.android.material.badge.ExperimentalBadgeUtils
    private fun addQuestionCountBadge() {
        BadgeDrawable.create(requireContext()).apply {
            isVisible = true
            number = 1
        }.let { badge ->
            BadgeUtils.attachBadgeDrawable(
                badge,
                binding.tbEventDetailsCommunication,
                R.id.actionOrganiserEventQuestions
            )
        }
    }

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
        pagerAdapter = CommunicationPanelsAdapter(this, args.isOrganiser)
        binding.vp2CommunicationPanels.adapter = pagerAdapter
    }

    private fun attachTabLayoutMediator() {
        TabLayoutMediator(
            binding.tlCommunicationPanels,
            binding.vp2CommunicationPanels
        ) { tab, position ->
            tab.text = pagerAdapter.getTabName(position)
        }.attach()
    }
}