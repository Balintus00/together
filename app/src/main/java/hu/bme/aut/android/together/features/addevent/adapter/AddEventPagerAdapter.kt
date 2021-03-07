package hu.bme.aut.android.together.features.addevent.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.*

class AddEventPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    // Order of elements in this array is important!
    private val fragmentPagesArray = arrayOf(
        NameAdderFragment(),
        VisibilityChooserFragment(),
        CategoryPickerFragment(),
        PhotoUploaderFragment(),
        DateSetterFragment(),
        PlacePickerFragment(),
        DescriptionGiverFragment(),
        OverViewFragment()
    )

    override fun getItemCount(): Int {
        return fragmentPagesArray.size
    }

    override fun createFragment(position: Int): Fragment {
        require(position >= 0 && position < fragmentPagesArray.size)
        return fragmentPagesArray[position]
    }
}