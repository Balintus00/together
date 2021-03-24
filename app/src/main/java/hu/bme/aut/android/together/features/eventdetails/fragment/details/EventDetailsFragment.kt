package hu.bme.aut.android.together.features.eventdetails.fragment.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentEventDetailsBinding

class EventDetailsFragment : Fragment(), OnMapReadyCallback {

    //TODO these navigation arguments will be later removed, when actual data will be used
    private val args: EventDetailsFragmentArgs by navArgs()

    //organiser, private, limitedParticipantCount, isParticipant
    private lateinit var optionsArray: Array<Boolean>

    private lateinit var binding: FragmentEventDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrieveOptionsArray()
    }

    private fun retrieveOptionsArray() {
        optionsArray = arrayOf(
            args.isOrganiser,
            args.isPrivate,
            args.isParticipantCountLimited,
            args.isParticipant
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUiWidgets(savedInstanceState)
        handleBackPressed()
    }

    private fun setUpUiWidgets(savedInstanceState: Bundle?) {
        setEventVisibilityTextVisibility()
        setUpFABByArguments()
        setUpMap(savedInstanceState)
        setUpToolbar()
        setShowWholeDescriptionTextBehaviour()
        setFABBehaviour()
    }

    private fun setEventVisibilityTextVisibility() {
        if (optionsArray[1])
            binding.tvPrivateEvent.visibility = View.VISIBLE
    }

    private fun setUpFABByArguments() {
        with(binding.fabActionEventDetails) {
            setImageResource(
                when {
                    optionsArray[0] -> R.drawable.ic_action_settings
                    optionsArray[3] -> R.drawable.ic_action_message
                    else -> R.drawable.ic_action_group_add
                }
            )

        }
    }

    private fun setUpMap(savedInstanceState: Bundle?) {
        binding.mvEventLocation.onCreate(savedInstanceState)
        binding.mvEventLocation.getMapAsync(this)
    }

    private fun setUpToolbar() {
        with(binding.tbEventDetails) {
            setNavigationIcon(R.drawable.ic_action_arrow_back)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setShowWholeDescriptionTextBehaviour() {
        binding.tvShowMoreInformationEventDetails.setOnClickListener {
            EventDetailsFragmentDirections.actionEventDetailsFragmentToEventDetailsFullDescriptionFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }

    private fun setFABBehaviour() {
        when {
            optionsArray[0] -> {
                //Organiser
                setOrganiserFABBehaviour()
            }
            optionsArray[3] -> {
                //Participant
                setParticipantFABBehaviour()
            }
            else -> {
                //Non-participant
                setNonParticipantFABBehaviour()
            }
        }
    }

    private fun setOrganiserFABBehaviour() {
        binding.fabActionEventDetails.setOnClickListener {
            binding.fabActionEventDetails.isExpanded = true
        }
        setOrganiserSheetBehaviour()
    }

    private fun setOrganiserSheetBehaviour() {
        with(binding) {
            tvOrganiserActionShowMessages.setOnClickListener {
                EventDetailsFragmentDirections.actionEventDetailsFragmentToEventDetailsCommunicationFragment(
                    optionsArray[0]
                )
                    .let { action ->
                        findNavController().navigate(action)
                    }
                fabActionEventDetails.isExpanded = false
            }
            tvOrganiserActionInvitePeople.setOnClickListener {
                EventDetailsFragmentDirections.actionEventDetailsFragmentToEventInvitationSenderFragment()
                    .let { action ->
                        findNavController().navigate(action)
                    }
                fabActionEventDetails.isExpanded = false
            }
            tvOrganiserActionModifyEvent.setOnClickListener {
                EventDetailsFragmentDirections.actionEventDetailsFragmentToModifyEventDetailsFragment()
                    .let { action ->
                        findNavController().navigate(action)
                    }
                fabActionEventDetails.isExpanded = false
            }
            tvCloseOrganiserSheet.setOnClickListener {
                fabActionEventDetails.isExpanded = false
            }
        }
    }

    private fun setParticipantFABBehaviour() {
        binding.fabActionEventDetails.setOnClickListener {
            EventDetailsFragmentDirections.actionEventDetailsFragmentToEventDetailsCommunicationFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }

    private fun setNonParticipantFABBehaviour() {
        binding.fabActionEventDetails.setOnClickListener {
            Snackbar.make(
                binding.root,
                getString(R.string.message_event_join_event_details),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun handleBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.fabActionEventDetails.isExpanded)
                        binding.fabActionEventDetails.isExpanded = false
                    else
                        findNavController().popBackStack()
                }
            })
    }

    override fun onStart() {
        super.onStart()
        binding.mvEventLocation.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mvEventLocation.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mvEventLocation.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mvEventLocation.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mvEventLocation.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        binding.mvEventLocation.onDestroy()
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mvEventLocation.onLowMemory()
    }

    override fun onMapReady(map: GoogleMap?) {
        // TODO this will be changed to represent actual data
        map?.let {
            val hungary = LatLng(47.0, 19.0)
            it.addMarker(
                MarkerOptions().position(hungary).title("Marker in Hungary")
            )
            it.moveCamera(CameraUpdateFactory.newLatLng(hungary))
        } ?: displayMapLoadingError()
    }

    private fun displayMapLoadingError() {
        Snackbar.make(
            binding.root,
            getString(R.string.error_map_loading_no_information),
            Snackbar.LENGTH_LONG
        ).show()
    }
}