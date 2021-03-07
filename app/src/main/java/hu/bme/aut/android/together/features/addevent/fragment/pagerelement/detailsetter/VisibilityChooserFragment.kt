package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.databinding.FragmentVisibilityChooserBinding

class VisibilityChooserFragment : Fragment() {

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
        setCardClickBehaviour()
    }

    private fun setCardClickBehaviour() {
        with(binding) {
            cardPrivate.setOnClickListener {
                //TODO navigation was here
            }
            cardPublic.setOnClickListener {
                //TODO navigation was here
            }
        }
    }

}