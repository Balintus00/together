package hu.bme.aut.android.together.features.currentevents.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.databinding.FragmentCurrentEventListBinding

abstract class CurrentEventListFragment : Fragment() {

    protected lateinit var binding: FragmentCurrentEventListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFabBehaviour()
    }

    protected abstract fun setFabBehaviour()
}