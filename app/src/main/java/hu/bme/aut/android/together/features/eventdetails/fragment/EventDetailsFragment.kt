package hu.bme.aut.android.together.features.eventdetails.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentEventDetailsBinding

class EventDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEventDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUiWidgets()
    }

    private fun setUpUiWidgets() {
        setUpToolbar()
        setShowWholeDescriptionTextBehaviour()
    }

    private fun setUpToolbar() {
        with(binding.tbEventDetails){
            setNavigationIcon(R.drawable.ic_action_arrow_back)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setShowWholeDescriptionTextBehaviour() {
        binding.tvShowMoreInformationEventDetails.setOnClickListener {
            //TODO
        }
    }
}