package hu.bme.aut.android.together.features.eventdetails.fragment.communication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import hu.bme.aut.android.together.databinding.FragmentEventDetailsCommunicationBinding
import hu.bme.aut.android.together.features.eventdetails.adapter.CommunicationPanelsAdapter

class EventDetailsCommunicationFragment : Fragment() {

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
        setNavigationButtonBehaviour()
        setUpTabBehaviour()
    }

    private fun setNavigationButtonBehaviour() {
        binding.tbEventDetailsCommunication.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpTabBehaviour() {
        initializeAndSetPagerAdapter()
        attachTabLayoutMediator()
    }

    private fun initializeAndSetPagerAdapter() {
        pagerAdapter = CommunicationPanelsAdapter(this)
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