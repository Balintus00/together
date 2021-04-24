package hu.bme.aut.android.together.features.eventcontrol.wholedescription.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import hu.bme.aut.android.together.databinding.FragmentEventDetailsFullDescriptionBinding

/**
 * This fragment displays the whole description of an event.
 */
class EventDetailsFullDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentEventDetailsFullDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailsFullDescriptionBinding.inflate(inflater, container, false)
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
}