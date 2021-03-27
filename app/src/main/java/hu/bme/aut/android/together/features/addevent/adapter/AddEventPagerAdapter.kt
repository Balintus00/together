package hu.bme.aut.android.together.features.addevent.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.container.PageableDetailSetterContainerFragment
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter.OverviewFragment
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.factory.PageableDetailSetterFragmentFactory

/**
 * This [FragmentStateAdapter] implementation can be used to control the event adding feature's
 * paging behaviour.
 * @param fragment the reference of the fragment, which contains the widget, that owns this adapter.
 */
class AddEventPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * This array of fragments contains the represented fragments of the adapter and its widget.
     * The order of fragments in this array determines their order in the paging.
     */
    private val fragmentPagesArray: Array<Fragment>

    init {
        fragmentPagesArray = createFragmentPagesArray()
    }

    /**
     * Creates the array, which will contain, which [Fragment]s in what kind of order should be
     * displayed. Currently it uses every instance of
     * [hu.bme.aut.android.together.features.addevent.fragment.pagerelement.factory.PageableDetailSetterFragmentFactory]
     * to populate the array. The order of fragments follows the ordinal values of the above referenced factory class.
     * The fragment's created by this method will be contained by an
     * [hu.bme.aut.android.together.features.addevent.fragment.pagerelement.container.PageableDetailSetterContainerFragment]
     * instance.
     * The first fragment's back navigation button will be hidden.
     * After this, an [hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter.OverviewFragment]
     * intance will be added as the last element of the array.
     * @return Array of [Fragment]s with the proper content.
     */
    private fun createFragmentPagesArray(): Array<Fragment> {
        val mutableArray = mutableListOf<Fragment>()
        PageableDetailSetterFragmentFactory.values().forEach {
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
        return mutableArray.toTypedArray()
    }

    override fun getItemCount(): Int {
        return fragmentPagesArray.size
    }

    override fun createFragment(position: Int): Fragment {
        require(position >= 0 && position < fragmentPagesArray.size)
        return fragmentPagesArray[position]
    }
}