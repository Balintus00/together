package hu.bme.aut.android.together.features.eventdetails.fragment.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentEventDetailsBinding

class EventDetailsFragment : Fragment(), OnMapReadyCallback {

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
        setUpUiWidgets(savedInstanceState)
    }

    private fun setUpUiWidgets(savedInstanceState: Bundle?) {
        setUpMap(savedInstanceState)
        setUpToolbar()
        setShowWholeDescriptionTextBehaviour()
        setCommunicationFABBehaviour()
    }

    private fun setUpMap(savedInstanceState: Bundle?) {
        binding.mvEventLocation.onCreate(savedInstanceState)
        //TODO scrollview should cooperate with this view
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

    private fun setCommunicationFABBehaviour() {
        binding.fabEventCommunication.setOnClickListener {
            EventDetailsFragmentDirections.actionEventDetailsFragmentToEventDetailsCommunicationFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
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
        // TODO this will be changed
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