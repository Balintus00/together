package hu.bme.aut.android.together.features.addevent.fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.databinding.FragmentPublicEventParticipantQuantifierBinding
import hu.bme.aut.android.together.features.addevent.dialogfragment.EventParticipantCountSpecifierDialogFragment

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

    private fun setOptionsBehaviour() {
        setUndefinedOptionBehaviour()
        setSpecifyCountOptionBehaviour()
    }

    private fun setUndefinedOptionBehaviour() {
        // TODO passing the set value using ViewModel
        binding.tvInfiniteParticipantOption.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setSpecifyCountOptionBehaviour() {
        // TODO passing the set value using ViewModel
        binding.tvSpecifiyParticipantCountOption.setOnClickListener {
            EventParticipantCountSpecifierDialogFragment().show(
                parentFragmentManager,
                EventParticipantCountSpecifierDialogFragment::class.java.simpleName
            )
        }
    }
}