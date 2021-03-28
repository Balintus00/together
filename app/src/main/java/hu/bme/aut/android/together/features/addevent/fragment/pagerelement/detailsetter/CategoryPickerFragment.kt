package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentCategoryPickerBinding

/**
 * This [Fragment] provides an user interface, that can be used by the user to set the category of
 * the event which is under creation.
 */
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

    /**
     * Adds the categories to the fragment [com.google.android.material.chip.ChipGroup] as
     * [Chip]s. The list of categories is contained by [R.array.event_category_types_array].
     */
    private fun addCategoryChips() {
        val categoryArray = resources.getStringArray(R.array.event_category_types_array)
        for (category in categoryArray) {
            binding.cgCategory.addView(createCategoryChip(category))
        }
    }

    /**
     * Creates an appropriately set [Chip], which will contain a category.
     * This chips has the [R.style.Widget_MaterialComponents_Chip_Choice] style.
     */
    private fun createCategoryChip(name: String): Chip {
        return Chip(requireContext()).apply {
            text = name
            setChipDrawable(
                ChipDrawable.createFromAttributes(
                    requireContext(),
                    null,
                    0,
                    R.style.Widget_MaterialComponents_Chip_Choice
                )
            )
        }
    }
}