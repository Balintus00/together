package hu.bme.aut.android.together.features.eventdetails.fragment.communication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.databinding.FragmentQABinding
import hu.bme.aut.android.together.features.eventdetails.adapter.EventQAAdapter

class QAFragment : Fragment() {

    private lateinit var binding: FragmentQABinding

    private lateinit var adapter: EventQAAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        adapter = EventQAAdapter(requireContext())
        binding.rvQuestionsAndAnswers.adapter = adapter
        binding.rvQuestionsAndAnswers.layoutManager = LinearLayoutManager(requireContext())
    }
}