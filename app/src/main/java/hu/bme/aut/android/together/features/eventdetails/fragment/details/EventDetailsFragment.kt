package hu.bme.aut.android.together.features.eventdetails.fragment.details

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
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
import java.util.*

/**
 * This fragment instance can be used to display an event's details.
 */
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

    //TODO these navigation arguments will be later removed, when actual data will be used
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
        setUpUIWidgets(savedInstanceState)
        handleBackPressed()
    }

    private fun setUpUIWidgets(savedInstanceState: Bundle?) {
        setUpRoleDependentUIWidgets()
        setUpNonRoleDependentUiWidgets(savedInstanceState)
    }

    private fun setUpRoleDependentUIWidgets() {
        when {
            optionsArray[0] -> setUpWidgetsAsOrganiser()
            optionsArray[3] -> setUpWidgetsAsParticipant()
            else -> setUpWidgetsAsNonParticipant()
        }
    }

    private fun setUpWidgetsAsOrganiser() {
        setUpOrganiserFAB()
    }

    /**
     * Sets the fragment's FAB to behave properly for an event organiser user.
     * Sets the appropriate image resource [R.drawable.ic_action_settings], and sets the behaviour
     * of the button. When the button is clicked, then a menu will be displayed to organiser, that
     * contains the organiser options.
     */
    private fun setUpOrganiserFAB() {
        with(binding.fabActionEventDetails) {
            setImageResource(R.drawable.ic_action_settings)
            setOnClickListener {
                binding.fabActionEventDetails.isExpanded = true
            }
        }
        setOrganiserSheetBehaviour()
    }

    /**
     * Sets the FAB's sheet behaviour when it is being used by an user with organiser role.
     * When the sheet is opened, it displays four options for the organiser user:
     * - Navigate to the event's communication screen
     * - Navigate to the invite sender screen
     * - Navigate to the event modification screen
     * - Close the sheet
     */
    private fun setOrganiserSheetBehaviour() {
        with(binding) {
            tvOrganiserActionShowMessages.setOnClickListener {
                navigateToEventCommunication(true)
                fabActionEventDetails.isExpanded = false
            }
            tvOrganiserActionInvitePeople.setOnClickListener {
                navigateToInvitationSenderScreen()
                fabActionEventDetails.isExpanded = false
            }
            tvOrganiserActionModifyEvent.setOnClickListener {
                navigateToEventModificationScreen()
                fabActionEventDetails.isExpanded = false
            }
            tvCloseOrganiserSheet.setOnClickListener {
                fabActionEventDetails.isExpanded = false
            }
        }
    }

    private fun navigateToEventCommunication(isOrganiser: Boolean) {
        EventDetailsFragmentDirections.actionEventDetailsFragmentToEventDetailsCommunicationFragment(
            isOrganiser = isOrganiser
        )
            .let { action ->
                findNavController().navigate(action)
            }
    }

    private fun navigateToInvitationSenderScreen() {
        EventDetailsFragmentDirections.actionEventDetailsFragmentToEventInvitationSenderFragment()
            .let { action ->
                findNavController().navigate(action)
            }
    }

    private fun navigateToEventModificationScreen() {
        EventDetailsFragmentDirections.actionEventDetailsFragmentToModifyEventDetailsFragment()
            .let { action ->
                findNavController().navigate(action)
            }
    }

    private fun setUpWidgetsAsParticipant() {
        setUpParticipantFAB()
    }

    /**
     * Sets the fragment's FAB to behave properly for a participant user.
     * Sets the appropriate image resource [R.drawable.ic_action_message], and sets the behaviour
     * of the button. When the button is clicked, then the user will be navigated to event's
     * communication screen.
     */
    private fun setUpParticipantFAB() {
        with(binding.fabActionEventDetails) {
            setImageResource(R.drawable.ic_action_message)
            setOnClickListener {
                navigateToEventCommunication(false)
            }
        }
    }

    private fun setUpWidgetsAsNonParticipant() {
        setUpNonParticipantFAB()
    }

    /**
     * Sets the fragment's FAB to behave properly for a non-participant user.
     * Sets the appropriate image resource [R.drawable.ic_action_group_add], and sets the behaviour
     * of the button. When the button is clicked, then an invitation request will be sent by
     * the user.
     */
    private fun setUpNonParticipantFAB() {
        with(binding.fabActionEventDetails) {
            setImageResource(R.drawable.ic_action_group_add)
            setOnClickListener {
                displayJoinRequestFeedback()
            }
        }
    }

    /**
     * Displays a [Snackbar] to the user, which confirms, that the invitation request sending
     * was successful.
     */
    private fun displayJoinRequestFeedback() {
        Snackbar.make(
            binding.root,
            getString(R.string.message_event_join_event_details),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setUpNonRoleDependentUiWidgets(savedInstanceState: Bundle?) {
        setEventVisibilityTextVisibility()
        setUpMap(savedInstanceState)
        setUpToolbar()
        setShowWholeDescriptionTextBehaviour()
    }

    private fun setEventVisibilityTextVisibility() {
        if (optionsArray[1])
            binding.tvPrivateEvent.visibility = View.VISIBLE
    }

    private fun setUpMap(savedInstanceState: Bundle?) {
        binding.mvEventLocation.onCreate(savedInstanceState)
        binding.mvEventLocation.getMapAsync(this)
    }

    /**
     * Sets the contained toolbar's navigation icon's behaviour.
     * When the icon is clicked, then BackStack will be popped.
     */
    private fun setUpToolbar() {
        with(binding.tbEventDetails) {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    /**
     * Sets the onclick behaviour of the TextView, that can be used to navigate to the screen
     * (implemented by [EventDetailsFullDescriptionFragment]), that displays the whole description
     * of the event.
     */
    private fun setShowWholeDescriptionTextBehaviour() {
        binding.tvShowMoreInformationEventDetails.setOnClickListener {
            EventDetailsFragmentDirections.actionEventDetailsFragmentToEventDetailsFullDescriptionFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }

    /**
     * Modifies the back button's onclick behaviour. If the organiser sheet is opened (visible) at
     * the moment, when the back button is clicked, then the BackStack won't be popped, instead the
     * organiser sheet will be closed. If the sheet is not opened (invisible) then the BackStack
     * will be popped on Back button click.
     */
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

    /**
     * Tries to set a Marker on the map on the location of the event. If something goes wrong,
     * the user will be notified about the failure using [displayMapLoadingError].
     */
    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            tryToGeocodeLocationAndMarkItOnMap(it)
        } ?: displayMapLoadingError()
    }

    /**
     * Displays a [Snackbar] to the user, that informs it, that the map loading operation
     * was unsuccessful.
     */
    private fun displayMapLoadingError() {
        Snackbar.make(
            binding.root,
            getString(R.string.error_map_loading_no_information),
            Snackbar.LENGTH_LONG
        ).show()
    }

    /**
     * Tries to geocode the represented event's location and to set a marker on the map using this
     * geocoded location. If the operation is unsuccessful, it notifies the user about the failure
     * of the operation by calling [displayMapLoadingError].
     */
    private fun tryToGeocodeLocationAndMarkItOnMap(map: GoogleMap) {
        try {
            setMapMarkerToGeocodedPosition(map, geocodeLocation())
        } catch (exception: Exception) {
            Log.i("Together!", exception.stackTraceToString())
            displayMapLoadingError()
        }
    }

    /**
     * Geocodes the event's location using [Geocoder].
     * @throws IllegalStateException if the [Geocoder] returns no address.
     * @throws java.io.IOException if the used [Geocoder.getFromLocationName] function fails.
     * @return the event's geocoded location.
     */
    private fun geocodeLocation(): LatLng {
        with(Geocoder(requireContext(), Locale.ENGLISH)) {
            //TODO actual location should be used later
            getFromLocationName("Irinyi József utca 42., Budapest", 1).let { addressList ->
                if (addressList.size == 0)
                    throw IllegalStateException("No address was found!")
                else
                    return addressList[0].let { address ->
                        LatLng(address.latitude, address.longitude)
                    }
            }
        }
    }

    /**
     * Places a marker on the map to event's location.
     * @param map the map, that the marker will be set on.
     * @param position the geocoded position of the event's location.
     */
    private fun setMapMarkerToGeocodedPosition(map: GoogleMap, position: LatLng) {
        map.apply {
            addMarker(
                MarkerOptions().position(
                    position
                )
                    .title(getString(R.string.marker_title_details_event_location))
            )
            moveCamera(
                CameraUpdateFactory.newLatLng(
                    position
                )
            )
        }
    }
}