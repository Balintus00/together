package hu.bme.aut.android.together.features.addevent.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.databinding.FragmentPublicEventParticipantQuantifierBinding

class PublicEventParticipantQuantifierFragment : Fragment() {

    private lateinit var binding: FragmentPublicEventParticipantQuantifierBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentPublicEventParticipantQuantifierBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOptionsBehaviour()
    }

    private fun setOptionsBehaviour(){
        setUndefinedOptionBehaviour()
        setSpecifyCountOptionBehaviour()
    }

    private fun setUndefinedOptionBehaviour(){

    }

    private fun setSpecifyCountOptionBehaviour(){

    }
}