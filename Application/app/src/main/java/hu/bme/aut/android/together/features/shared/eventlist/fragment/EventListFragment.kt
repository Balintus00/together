package hu.bme.aut.android.together.features.shared.eventlist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.together.databinding.FragmentEventListBinding
import hu.bme.aut.android.together.features.shared.eventlist.adapter.EventListAdapter

class EventListFragment : Fragment() {

    private lateinit var binding: FragmentEventListBinding

    private val adapter = EventListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvEventList.apply{
            layoutManager = LinearLayoutManager(view.context)
            adapter = this@EventListFragment.adapter
        }
    }

}