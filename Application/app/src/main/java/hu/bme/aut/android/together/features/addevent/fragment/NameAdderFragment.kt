package hu.bme.aut.android.together.features.addevent.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.databinding.FragmentNameAdderBinding

class NameAdderFragment : Fragment() {

    private lateinit var binding: FragmentNameAdderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNameAdderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNextButtonBehaviour()
    }

    private fun setNextButtonBehaviour() {
        binding.btnNameAdderNext.setOnClickListener {
            NameAdderFragmentDirections.actionNameAdderFragmentToVisibilityChooserFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }

}