package hu.bme.aut.android.together.features.addevent.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.container.PageableDetailSetterContainerFragment
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter.OverviewFragment
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.factory.PageableDetailFragmentFactory

class AddEventPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    // Order of elements in this array is important!
    private val fragmentPagesArray: Array<Fragment>

    init {
        // Setting up fragmentPagesArray
        val mutableArray = mutableListOf<Fragment>()
        PageableDetailFragmentFactory.values().forEach {
            if (it.ordinal == 0)
                mutableArray.add(
                    PageableDetailSetterContainerFragment.newInstance(
                        it.ordinal,
                        showBackKey = false
                    )
                )
            else
                mutableArray.add(PageableDetailSetterContainerFragment.newInstance(it.ordinal))
        }
        mutableArray.add(OverviewFragment())
        fragmentPagesArray = mutableArray.toTypedArray()
    }

    override fun getItemCount(): Int {
        return fragmentPagesArray.size
    }

    override fun createFragment(position: Int): Fragment {
        require(position >= 0 && position < fragmentPagesArray.size)
        return fragmentPagesArray[position]
    }
}