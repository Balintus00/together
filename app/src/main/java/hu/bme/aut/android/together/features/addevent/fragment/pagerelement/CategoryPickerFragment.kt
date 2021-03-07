package hu.bme.aut.android.together.features.addevent.fragment.pagerelement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.chip.Chip
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentCategoryPickerBinding

class CategoryPickerFragment : Fragment() {

    private lateinit var binding: FragmentCategoryPickerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCategoryChips()
    }

    private fun addCategoryChips() {
        val categoryArray = resources.getStringArray(R.array.event_category_types_array)
        for (category in categoryArray) {
            binding.cgCategory.addView(createCategoryChip(category))
        }
    }

    private fun createCategoryChip(name: String): Chip {
        return Chip(requireContext()).apply {
            text = name
            setOnClickListener {
                //TODO the chosen category will also be passed later
                //TODO navigation was here before
            }
        }
    }
}