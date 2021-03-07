package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.databinding.FragmentDescriptionGiverBinding

class DescriptionGiverFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionGiverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionGiverBinding.inflate(inflater, container, false)
        return binding.root
    }
}